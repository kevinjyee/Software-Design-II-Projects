/**
  Grocery Class
  Solves EE422C programming assignment #3
  @author Kevin Yee (kjy252), Alvin Tung (ayt243)
  @version 1.00 2016-02-023
 */
 
package Assignment3;

public class Grocery extends Item 
{
// Instance variables (protected to allow inheriting them)
    protected boolean perishable;
    
// Constructors    
    /**
     * Create an grocery item with a name, price, initial quantity, weight, and perishability
     * @param name        The name of the grocery
     * @param price       The price of the grocery
     * @param quantity    The initial quantity of the grocery 
     * @param weight      The weight of the grocery
     * @param perishable  The perishability of the grocery
     */  
    public Grocery(String name, double price, int quantity, int weight, boolean perishable)
    {
        super(name, price, quantity, weight);
        this.perishable = perishable;        
    }

// methods for final price and printing

    /**
     * Calculates the final price based on shipping 
     * @return double  The final price of grocery
     */  
    double calculatePrice () 
    {
        // No sales Tax
                
        // Shipping cost 
        double shipping_cost = 20*(this.weight); 
        if(this.perishable){
            shipping_cost = shipping_cost*(1.20);
        }
        
        // Final price for the total quantity of grocery 
        double final_price = quantity * (shipping_cost + price);
        
        // Insert price calculation here
        return final_price;
    }
    
    /**
     * Prints the instance variable attributes in a readable way 
     */         
    void printItemAttributes() 
    {   
        // Create String for attributes
        String output_name = "Item: " + this.name + "\n";
        String output_price = "Price: $" + String.format("%1$,.2f", this.price) + "\n";
        String output_quantity = "Quantity: " + this.quantity + "\n";;
        String output_weight = "Weight: " + (this.weight*this.quantity) + "\n";
        String output_shipping = "Shipping: "; 
            if(perishable) output_shipping += ("Perishable");
            else output_shipping += ("Non-perishable");
        
        //Print all attributes of this class
        System.out.println(output_name + output_price + output_quantity + output_weight + output_shipping);
    }
    
// Get and set methods
    
    /**
     * @return The grocery's perishability.
     */    
    public boolean getPerishable(){
        return perishable;
    }
    
    /**
     * Set the grocery's perishability.
     * @param perishable  The new perishability.
     */ 
    public void setPerishable(boolean perishable){
        this.perishable = perishable;
    }    
}
