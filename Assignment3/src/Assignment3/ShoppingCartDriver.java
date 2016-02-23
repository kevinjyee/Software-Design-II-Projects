/*
* Kevin Yee 
* Alvin Tung
*/
package Assignment3;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class ShoppingCartDriver 
	{

/******************************************************************************
	* Method Name: processLinesInFile                                             *
	* Purpose: Opens the file specified in String filename, reads each line in it *
	*          Invokes translate () on each line in the file, and prints out the  *
	*          translated piglatin string.                                        *
	* Returns: None                                                               
 * @throws Exception *
	******************************************************************************/
	public static void processLinesInFile (String filename) throws Exception 
	{ 

		
		ShoppingCartDriver driver = new ShoppingCartDriver();
		try 
		{
			FileReader freader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(freader);
			
			for (String s = reader.readLine(); s != null; s = reader.readLine()) 
			{
				String output = driver.processCommands(s);
				System.out.println(output);
			}
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println ("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) 
		{
			System.err.println ("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		}
		return;
	}
	
	
	  public static ArrayList<Item> shoppingCart = new ArrayList<Item>(); 
	  
	 public static String[] STATES = new String[] {"al", "ak", "as", "az", "ar", "ca", "co", "ct", "de", "dc", "fm", "fl", "ga", "gu", "hi", "id", "il", "in", "ia", "ks", "ky", "la", "me", "mh", "md", "ma", "mi", "mn", "ms", "mo", "mt", "ne", "nv", "nh", "nj", "nm", "ny", "nc", "nd", "mp", "oh", "ok", "or", "pw", "pa", "pr", "ri", "sc", "sd", "tn", "tx", "ut", "vt", "vi", "va", "wa", "wv", "wi", "wy"};
	 public static String[] EXSTATES = new String[] {"tx","nm","va","az","ak"};
	 public static ArrayList<String > ALL_STATES = new ArrayList(Arrays.asList(STATES));
	 public static ArrayList<String> EXCEPTION_STATES = new ArrayList(Arrays.asList(EXSTATES));
	  
	  public static void main(String[] args) throws Exception 
	  {
		
		
		  if (args.length != 1) 
			{
				System.err.println ("Error: Incorrect number of command line arguments");
				System.exit(-1);
			}
		  processLinesInFile (args[0]);
		
		//Parse input, take appropriate actions.
		
		//Stub for arraylist.
		
		
		// General code example for how to iterate an array list. You will have to modify this heavily, to suit your needs.
		Iterator<Item> i = shoppingCart.iterator();
		while (i.hasNext()) 
		{
			Item temp = i.next();
			temp.calculatePrice();
			temp.printItemAttributes();
			//This (above) works because of polymorphism: a determination is made at runtime, 
			//based on the inherited class type, as to which method is to be invoked. Eg: If it is an instance
			// of Grocery, it will invoke 
			//the calculatePrice () method defined in Grocery.
		}		
	  }

	 public static String processCommands(String input) throws Exception{
		  
		 String commands[] = input.toLowerCase().split("\\s+");
		 if(commands.length > 8){return "Invalid Number of Arguments";}
		 
		 
		 if (commands[0].equals("insert")) {
				return processInsert(commands);
			} else if (commands[0].equals("search")) {
				return processSearch(commands);
			} else if (commands[0].equals("delete")) {
				return processDelete(commands);
			} else if (commands[0].equals("update")) {
				return processUpdate(commands);
			} else if (commands[0].equals("print")) {
				processPrint(commands);
			} else {
				return "Invalid Input"; 
				
			}
		return "";
	  }
	 
	 public static String processInsert(String[] input) throws Exception
	 {
		 if(input.length > 8){
			return "Invalid Input";
		 }
		 
		 String type = input[1];
		 String name = input[2];
		 double price = Double.parseDouble(input[3]);
		 int quantity = Integer.parseInt(input[4]);
		 int weight = Integer.parseInt(input[5]);
		 
		 if(type.equals("groceries"))
		 {
			 if(input.length != 7){
				 return "Invalid Input";
			 }
			 
			 boolean perishable;
			
			 if(input[6].equals("p"))
			 {
				 perishable = true;
			 }
			 else if(input[6].equals("np"))
			 {
				 perishable = false;
			 }
			 else
			 {
				 return "Invalid Input";
			 }
			 Grocery newGrocery = new Grocery(name, price, quantity,weight,perishable);
			 shoppingCart.add(newGrocery);
		 }
		 
		 if(type.equals("clothings"))
		 {
			 if(input.length != 6){
				 return "Invalid Input";
			 }
			 
			 Clothing newClothing = new Clothing(name, price, quantity,weight);
			 shoppingCart.add(newClothing);
		 } 
		 
		 
		 if(type.equals("electronics"))
		 {
			 if(input.length != 8)
			 {
				 return "Invalid Input";
			 }
			 
			
			 
			 String breakable = input[6];
			 String state = input[7];
			 boolean fragile;
			 
			 
			 if(breakable.equals("f")){
				 fragile = true;
			 }
			 else if(breakable.equals("nf"))
			 {
				 fragile = false;
			 }
			 else{
				 return "Invalid Input";
			 }
			 
			 if(!ALL_STATES.contains(state)){
				 return "Invalid Input";
			 }
			 
			 Electronics newElectronics = new Electronics(name, price, quantity,weight,fragile,state);
			 shoppingCart.add(newElectronics); 
					 
		 }
		 return "Added " + name+ " to cart";
	 }
	 
	 public static String processSearch(String[] input)
	 {
		 if(input.length != 2){
			 System.out.println("Invalid Input");
			 return "";
		 }
		 String name = input[1];
		 
		 int index = findName(name);
		 
		 if(index != -1){
			 return String.format("There are %s of %d", name, shoppingCart.get(index).getQuantity());
		 }
		 else{
			return "Item Not Found";
		 }
		 
		
	 }
	 
	 public static String processDelete(String[] input)
	 {
	 
		 if(input.length != 2){
			 System.out.println("Invalid Input");
			 return "";
		 }
		 String name = input[1];
		 
		 int index = findName(name);
		 
		 if(index != -1){
			 System.out.println(String.format("Deleted quantity of %s to %d", name, shoppingCart.get(index).getQuantity()));
			 shoppingCart.remove(index);
		 }
		 else{
			 System.out.println("Item Not Found");
		 }
		 
		 return "";
	 }
 
	 public static String processUpdate(String[] input)
	 { 
		 if(input.length != 3){
			return "Invalid Input";
		 }
		 String name = input[1];
		 int quantity = Integer.parseInt(input[2]);
		 
		 int index = findName(name);
		 
		 if(index != -1){
			 shoppingCart.get(index).setQuantity(quantity);
			 return String.format("Updated quantity of %s to %d", name, shoppingCart.get(index).getQuantity());
		 }
		 else{
			 System.out.println("Item Not Found");
		 }
		 
		 return "";
	 }

	 public static String processPrint(String[] input)
	 {
		 if(input.length != 1){
			 System.out.println("Invalid Input");
			 return "";
		 }
		 Collections.sort(shoppingCart);
		 
		 for(int i =0; i < shoppingCart.size(); i++){
			 shoppingCart.get(i).printItemAttributes();
		 }
		 return "";
	
	 }
	 
	 
	 public static int findName(String name){
		 int ind = -1;
		 if(shoppingCart.size() == 0){return ind;}
		 for(ind = 0; ind < shoppingCart.size(); ind++){
			 if(shoppingCart.get(ind).getName().equals(name)){
				return ind; 
				
			 }
		 }
		 
	 
	 return -1;
	}
	 
	}


	
	


