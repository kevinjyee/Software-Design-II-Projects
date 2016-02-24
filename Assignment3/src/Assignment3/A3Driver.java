/**
  A3Driver Class
  Solves EE422C programming assignment #3
  @author Kevin Yee (kjy252), Alvin Tung (ayt243)
  @version 1.00 2016-02-023
 */
 
package Assignment3;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class A3Driver
{

    /******************************************************************************
     * Method Name: processLinesInFile                                             *
     * Purpose: Opens the file specified in String filename, reads each line in it *
     *          Invokes translate () on each line in the file, and prints out the  *
     *          corresponding outputs.                                             *
     * Returns: None                                                               *
     * @throws Exception                                                           *
     ******************************************************************************/
    public static void processLinesInFile (String filename) throws Exception 
    {
        A3Driver driver = new A3Driver();
        try 
        {
            // Read file 
            FileReader freader = new FileReader(filename);
            BufferedReader reader = new BufferedReader(freader);
            
            // Iterate over and process each line of file
            for (String s = reader.readLine(); s != null; s = reader.readLine()) 
            {
                String output = driver.processCommands(s); // process command 
                System.out.println(output);                // output corresponding output
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
    
// Instance variables
    
    // ArrayList of items held in shopping cart
    public static ArrayList<Item> shoppingCart = new ArrayList<Item>(); 
    // Constants for state abbreviations
    public static final String[] STATES = new String[] {"al", "ak", "as", "az", "ar", "ca", "co", "ct", "de", "dc", "fm", "fl", "ga", "gu", "hi", "id", "il", "in", "ia", "ks", "ky", "la", "me", "mh", "md", "ma", "mi", "mn", "ms", "mo", "mt", "ne", "nv", "nh", "nj", "nm", "ny", "nc", "nd", "mp", "oh", "ok", "or", "pw", "pa", "pr", "ri", "sc", "sd", "tn", "tx", "ut", "vt", "vi", "va", "wa", "wv", "wi", "wy"};
    public static final String[] EXSTATES = new String[] {"tx","nm","va","az","ak"};
    public static final ArrayList<String > ALL_STATES = new ArrayList(Arrays.asList(STATES));
    public static final ArrayList<String> EXCEPTION_STATES = new ArrayList(Arrays.asList(EXSTATES));
    // regex String to check double 
    private static final String regDOUBLE =  "^\\d+(\\.\\d+)?$";

    /******************************************************************************
     * Method Name: main                                                           *
     * Purpose: Driver to test shopping cart and item classes                      *
     *          (Electronics, Grocery, Clothing)                                   *
     * Returns: None                                                               *
     ******************************************************************************/
    public static void main(String[] args) throws Exception
    {
        if (args.length != 1)
        {
            System.err.println ("Error: Incorrect number of command line arguments");
            System.exit(-1);
        }
        processLinesInFile (args[0]);
    }

    /**
     * Process the first input token as a command
     * @param input     string input to parse and execute
     * @return String   command's corresponding output 
     */ 
    public static String processCommands(String input) throws Exception
    {
        // Split input string into tokens 
        String commands[] = input.split("\\s+");
        if(commands.length > 8 || commands.length <= 0) {
            return "Invalid Input: Number of Arguments";
        }
        
        // Find command to execute 
        if (commands[0].equalsIgnoreCase("insert")) {          
            return processInsert(commands);
        } else if (commands[0].equalsIgnoreCase("search")) {
            return processSearch(commands);
        } else if (commands[0].equalsIgnoreCase("delete")) {
            return processDelete(commands);
        } else if (commands[0].equalsIgnoreCase("update")) {
            return processUpdate(commands);
        } else if (commands[0].equalsIgnoreCase("print")) {
            return processPrint(commands);
        } else {
            return "Invalid Input: Command";
        }
    }

    /**
     * Process insert on the remaining tokens from input
     * @param input     string[] input to parse and execute
     * @return String   insert's corresponding output 
     */ 
    public static String processInsert(String[] input) throws Exception
    {
        // Check number of arguments
        if(input.length > 8 || input.length < 6){
           return "Invalid Input: Number of Arguments";
        }

        // Parse remaining inputs 
        String type = input[1];
        String name = input[2];
        
        // Check Price input for error       
        double price;        
        if(!input[3].matches(regDOUBLE)){ return "ERROR: Please enter amount in 0.00 format";}
        try { price = Double.parseDouble(input[3]);}
        catch(NumberFormatException e) { return "Invalid Input: Price";}
        
        // Check Quantity input for error
        int quantity;
        try{ quantity = Integer.parseInt(input[4]);}
        catch(NumberFormatException e){ return "Invalid Input: Quantity";}
        
        // Check Weight input for error        
        int weight;
        try{ weight = Integer.parseInt(input[5]); }
        catch(NumberFormatException e){ return "Invalid Input: Weight ";}
        
        // Check Price, Quantity, and Weight for negative values
        if(weight < 0 || quantity < 0 || price < 0){ return "Invalid Input: Negative Value";}

        // Process for Grocery item
        if(type.equalsIgnoreCase("groceries"))
        {
            // Check number of arguments
            if(input.length != 7){ 
                return "Invalid Input: Number of Arguments";
            }
            
            // Check Perishable input
            String perishability = input[6]; 
            boolean perishable;
            if(perishability.equalsIgnoreCase("p")) {
                perishable = true;
            } else if(perishability.equalsIgnoreCase("np")) {
                perishable = false;
            } else {
                return "Invalid Input: Perishable";
            }
            
            // Add Grocery item to cart 
            Grocery newGrocery = new Grocery(name, price, quantity,weight,perishable);
            shoppingCart.add(newGrocery);
        }
        
        // Process for Clothing item
        else if(type.equalsIgnoreCase("clothing"))
        {
            // Check number of arguments            
            if(input.length != 6){
                return "Invalid Input: Number of Arguments";
            }
            
            // Add Clothing item to cart             
            Clothing newClothing = new Clothing(name, price, quantity, weight);
            shoppingCart.add(newClothing);
        } 
        
        // Process for Electronics item
        else if(type.equalsIgnoreCase("electronics"))
        {
            // Check number of arguments            
            if(input.length != 8){
                return "Invalid Input: Number of Arguments";
            }
            
            // Check Fragile and State input            
            String fragility = input[6];
            String state = input[7];
            boolean fragile;
            if(fragility.equalsIgnoreCase("f")) {
                fragile = true;
            } else if(fragility.equalsIgnoreCase("nf")) {
                fragile = false;
            } else{
                return "Invalid Input: Fragile";
            }
            if(!ALL_STATES.contains(state.toLowerCase())){
                return "Invalid Input: State";
            }
            
            // Add Electronics item to cart                         
            Electronics newElectronics = new Electronics(name, price, quantity,weight,fragile,state);
            shoppingCart.add(newElectronics); 
        }
        
        // Return message for insert process
        return "Added " + name+ " to cart";
    }
    
    /**
     * Process search on the remaining tokens from input
     * @param input     string[] input to parse and execute
     * @return String   search's corresponding output 
     */     
    public static String processSearch(String[] input)
    {
        // Check number of arguments                    
        if(input.length != 2){
            return "Invalid Input: Number of Arguments";
        }
        
        // Parse remaining input
        String name = input[1];

        // Find all items with same name and return quantity
        boolean noneFound = true;
        int ind = 0;
        int numFound = 0;
        do{
            ind = findName(name, ind, shoppingCart.size());      // -1 if none found, else found at index  
            if(ind != -1){
                numFound += shoppingCart.get(ind).getQuantity(); // add quntity of found item 
                noneFound = false;                               // an item was found 
                ind++;                                           // increment to next index of shopping cart
            }
        } while(ind != -1);                                      // search again if item was found 

        // Return message for search process        
        if(!noneFound){
            return String.format("Search found %s. Amount: %d", name, numFound); 
        } else{
           return "Item Not Found";
        }    
    }

    /**
     * Process delete on the remaining tokens from input
     * @param input     string[] input to parse and execute
     * @return String   delete's corresponding output 
     */ 
    public static String processDelete(String[] input)
    {
        // Check number of arguments            
        if(input.length != 2){
            return "Invalid Input: Number of Arguments";
        }
        
        // Parse remaining input        
        String name = input[1];

        // Find all items with same name and return quantity
        boolean noneDeleted = true;
        int ind = 0;
        int numDeleted = 0;
        do{
            ind = findName(name, ind, shoppingCart.size());        // -1 if none found, else found at index  
            if(ind != -1){
                numDeleted += shoppingCart.get(ind).getQuantity(); // add quntity of found item 
                shoppingCart.remove(ind);                          // remove item found, do not increment because item removed
                noneDeleted = false;                               // an item was found and deleted
            }
        } while(ind != -1);                                        // search again if item was found 

        // Return message for delete process        
        if(!noneDeleted){
            return String.format("Deleted %s. Amount: %d", name, numDeleted);
        } else{
            return "Item Not Found";
        }
    }

    /**
     * Process update on the remaining tokens from input
     * @param input     string[] input to parse and execute
     * @return String   update's corresponding output 
     */      
    public static String processUpdate(String[] input)
    { 
        // Check number of arguments                
        if(input.length != 3){
           return "Invalid Input: Number of Arguments";
        }

        // Parse remaining input                
        String name = input[1];
        
        // Check Quantity input for error               
        int quantity = Integer.parseInt(input[2]);
        if(quantity < 0){
            return "Invalid Input: Negative Value";
        }
        
        // Search shopping cart for first instance of item with desired name
        int index = findName(name, 0, shoppingCart.size());
        
        // Return message for update process                
        if(index != -1){
            shoppingCart.get(index).setQuantity(quantity);
            return String.format("Updated quantity of %s to %d", name, shoppingCart.get(index).getQuantity());
        } else{
            return "Item Not Found";
        }
    }

    /**
     * Process print
     * @param input     string[] input to parse and execute
     * @return String   print's corresponding output 
     */          
    public static String processPrint(String[] input)
    {
        // Check number of arguments                        
        if(input.length != 1){
            return "Invalid Input";
        }
        
        // Sort shopping cart alphabetically
        Collections.sort(shoppingCart);

        // Iterate and print instance variables and final item cost
        System.out.println();
        double totalShoppingCartCost = 0;
        Iterator<Item> i = shoppingCart.iterator();
        while (i.hasNext())
        {
            Item temp = i.next();                     // grab next item                                      
            double tempPrice = temp.calculatePrice(); // calculate final price
            temp.printItemAttributes();               // print instance variables
            System.out.println("Total Cost: $" + String.format("%1$,.2f", tempPrice) + "\n");
                                                      // print out total cost for item
            totalShoppingCartCost += tempPrice;       // add final price to total shopping cart cost
        }
        
        // Return message for print process        
        return "Total Cost of Shopping Cart: $" + String.format("%1$,.2f", totalShoppingCartCost);
    }

    /**
     * Find item with name in shopping cart
     * @param name      name of item to find
     * @param startInd  index to start search, inclusively
     * @param endInd    index to end search, exclusively
     * @return int      index of matching item, -1 if none found 
     */         
    private static int findName(String name, int startInd, int endInd){
        int ind = -1; 
        if(endInd - startInd <= 0){ return ind;} // if startInd is greater or equal to endInd
        for(ind = startInd; ind < endInd; ind++){
            if(shoppingCart.get(ind).getName().equals(name)){
               return ind;  // return index of first instance of item
            }
        }
       return ind; // return -1 if not found
   }     
}

