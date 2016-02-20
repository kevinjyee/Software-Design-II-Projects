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
		ArrayList<Item> shoppingCart = new ArrayList<Item>(); 
		
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
