/**
  Electronics Class
  Solves EE422C programming assignment #3
  @author Kevin Yee (kjy252), Alvin Tung (ayt243)
  @version 1.00 2016-02-023
 */
 
package Assignment3;

import java.util.ArrayList;
import java.util.Arrays;

public class Electronics extends Item 
{
// Constants for state abbreviations
    public static final String[] STATES = new String[] {"al", "ak", "as", "az", "ar", "ca", "co", "ct", "de", "dc", "fm", "fl", "ga", "gu", "hi", "id", "il", "in", "ia", "ks", "ky", "la", "me", "mh", "md", "ma", "mi", "mn", "ms", "mo", "mt", "ne", "nv", "nh", "nj", "nm", "ny", "nc", "nd", "mp", "oh", "ok", "or", "pw", "pa", "pr", "ri", "sc", "sd", "tn", "tx", "ut", "vt", "vi", "va", "wa", "wv", "wi", "wy"};
    public static final String[] EXSTATES = new String[] {"tx","nm","va","az","ak"};
    public static final ArrayList<String > ALL_STATES = new ArrayList(Arrays.asList(STATES));
    public static final ArrayList<String> EXCEPTION_STATES = new ArrayList(Arrays.asList(EXSTATES));

// Instance variables (protected to allow inheriting them)    
    protected boolean fragile; 
    protected String state;
    
// Constructors    
    /**
     * Create an electronics item with a name, price, initial quantity, weight, fragility, and state
     * @param name     The name of the electronics
     * @param price    The price of the electronics
     * @param quantity The initial quantity of the electronics 
     * @param weight   The weight of the electronics
     * @param fragile  The fragility of the electronics
     * @param state    The state where the electronics was bought
     */    
    public Electronics(String name, double price, int quantity, int weight, boolean fragile, String state)
    {
        super(name, price, quantity, weight);
        this.fragile = fragile;
        this.state = state.toLowerCase();
    }
    
// methods for final price and printing
    
    /**
     * Calculates the final price based on tax rate and shipping 
     * @return double  The final price of electronics 
     */         
    public double calculatePrice () 
    {
        // Sales Tax
        double tax_cost;
        if(EXCEPTION_STATES.contains(state)){
            tax_cost = 0;
        } else{
            tax_cost = this.price * (.1); // 10% tax
        }
                
        // Shipping cost 
        double shipping_cost = 20*(this.weight); 
        if(this.fragile){
            shipping_cost = shipping_cost*(1.20);
        }
        
        // Final price for the total quantity of electronics 
        double final_price = quantity * (tax_cost + shipping_cost + price);
        
        return final_price;
    }

    /**
     * Prints the instance variable attributes in a readable way 
     */    
    public void printItemAttributes() 
    {   
        // Create String for attributes
        String output_name = "Name: " + this.name + "\n";
        String output_type = "Item Type: Electronics" + "\n";        
        String output_price = "Price: $" + String.format("%1$,.2f", this.price) + "\n";
        String output_quantity = "Quantity: " + this.quantity + "\n";;
        String output_weight = "Weight: " + (this.weight) + "\n";
        String output_shipping = "Shipping: "; 
            if(fragile) output_shipping += ("Fragile");
            else output_shipping += ("Non-fragile");
            output_shipping += " (" + state.toUpperCase() + ")";
        
        //Print all attributes of this class
        System.out.println(output_name + output_type + output_price + output_quantity + output_weight + output_shipping);
    }
    
// Get and set methods
    
    /**
     * @return The electronics' fragility.
     */
    public boolean getFragile(){
        return fragile;
    }   
    
    /**
     * Set the electronics' fragility.
     * @param weight  The new weight.
     */         
    public void setFragile(boolean fragile){
        this.fragile = fragile;
    }    

}
