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
	* Returns: None                                                               *
	******************************************************************************/
	public static void processLinesInFile (String filename) 
	{ 

		
		ShoppingCartDriver driver = new ShoppingCartDriver();
		try 
		{
			FileReader freader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(freader);
			
			for (String s = reader.readLine(); s != null; s = reader.readLine()) 
			{
				String output = driver.processCommands(s);
				
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
	
	  public static void main(String[] args) 
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

	 public static String processCommands(String input){
		  
		 String commands[] = input.split("\\s+");
		 if(commands.length > 8){System.out.println("Invalid Number of Arguments");}
		 
		 if (commands[0].equals("insert")) {
				return "";
			} else if (commands[0].equals("search")) {
				return "";
			} else if (commands[0].equals("delete")) {
				return "";
			} else if (commands[0].equals("update")) {
				return "";
			} else if (commands[0].equals("print")) {
				return "";
			} else {
				System.out.println("Invalid Argument");
				return "";
			}
		 
	  }
	 
	 public static String processInsert(String[] input)
	 {
		 if(input.length > 8){
			 System.out.println("Invalid Input");
			 return "";
		 }
		
		 String type = input[1];
		 String name = input[2];
		 double price = Double.parseDouble(input[3]);
		 int quantity = Integer.parseInt(input[4]);
		 double weight = Double.parseDouble(input[5]);
		 
		 if(type.toLowerCase().equals("groceries"))
		 {
			 if(input.length != 7){
				 System.out.println("Invalid Input");
				 return "";
			 }
			 
			 boolean perishable;
			
			 if(input[6].toLowerCase().equals("p"))
			 {
				 perishable = true;
			 }
			 else if(input[6].toLowerCase().equals("np"))
			 {
				 perishable = false;
			 }
			 else
			 {
				 System.out.println("Invalid Input");
				 return "";
			 }
			 Grocery newGrocery = new Grocery(name, price, quantity,weight,perishable);
			 shoppingCart.add(newGrocery);
		 }
		 
		 if(type.toLowerCase().equals("clothings"))
		 {
			 if(input.length != 6){
				 System.out.println("Invalid Input");
				 return "";
			 }
			 
			 Clothing newClothing = new Clothing(name, price, quantity,weight);
			 shoppingCart.add(newClothing);
		 } 
		 
		 
		 if(type.toLowerCase().equals("electronics"))
		 {
			 if(input.length != 8)
			 {
				 System.out.println("Invalid Input");
				 return "";
			 }
			 
			 //TODO check if fragile and input is actually a state
			 
			 String breakable = input[6];
			 String state = input[7];
			 boolean fragile;
			 
			 if(breakable.toLowerCase().equals("f")){
				 fragile = true;
			 }
			 else if(breakable.toLowerCase().equals("nf"))
			 {
				 fragile = false;
			 }
			 else{
				 System.out.println("Invalid Input");
				 return "";
			 }
		 }
		 return "";
	 }
	 
	 public static String processSearch(String[] input)
	 {
		 
		 
		 return "";
	 }
	 
	 public static String processDelete(String[] input)
	 {
	 
	 
		 return "";
	 }
 
	 public static String processUpdate(String[] input)
	 { 
	 	return "";
	 }

	 public static String processPrint(String[] input)
	 {
		return "";
	 }

}

