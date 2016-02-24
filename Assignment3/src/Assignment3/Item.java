/**
  Item Class
  Solves EE422C programming assignment #3
  @author Kevin Yee (kjy252), Alvin Tung (ayt243)
  @version 1.00 2016-02-023
 */

package Assignment3;

public class Item implements Comparable<Item>
{
// Instance variables (protected to allow inheriting them)
    protected String name;
    protected double price;
    protected int quantity;
    protected int weight;

// Constructors    
    /**
     * Create an item with a name, price, initial quantity, and weight
     * @param name     The name of the item
     * @param price    The price of the item
     * @param quantity The initial quantity of the item 
     * @param weight   The weight of the item
     */
    public Item(String name, double price, int quantity, int weight)
    {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.weight = weight;
    }
    
// methods for final price and printing

    /**
     * Calculates the final price based on tax rate and shipping 
     * @return double  The final price of items 
     */     
    public double calculatePrice () 
    {
        // Sales Tax
        double tax_cost = this.price * (.1); // 10% tax
                
        // Shipping cost 
        double shipping_cost = 20*(this.weight); 
        
        // Final price for the total quantity of item 
        double final_price = quantity * (tax_cost + shipping_cost + price);
        
        return final_price;
    }
    
    /**
     * Prints the instance variable attributes in a readable way 
     */     
    public void printItemAttributes() 
    {   
        // Create String for attributes
        String output_name = "Item: " + this.name + "\n";
        String output_price = "Price: $" + String.format("%1$,.2f", this.price) + "\n";
        String output_quantity = "Quantity: " + this.quantity + "\n";;
        String output_weight = "Weight: " + (this.weight*this.quantity);
        
        //Print all attributes of this class
        System.out.println(output_name + output_price + output_quantity + output_weight);
    }

// Get and set methods

    /**
     * @return The item's name.
     */
    public String getName(){
        return name;
    }

    /**
     * @return The item's price.
     */    
    public double getPrice(){
        return price;
    }    
 
     /**
     * @return The item's quantity.
     */
    public int getQuantity(){
        return quantity;
    }

     /**
     * @return The item's weight.
     */
    public int getWeight(){
        return weight;
    }
    
    /**
     * Set the item's name.
     * @param name  The new name.
     */ 
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * Set the item's price.
     * @param price  The new price.
     */     
    public void setPrice(double price){
        this.price = price;
    }    
 
    /**
     * Set the item's quantity.
     * @param quantity  The new quantity.
     */ 
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    /**
     * Set the item's weight.
     * @param weight  The new weight.
     */     
    public void setWeight(int weight){
        this.weight = weight;
    }

    // /**
     // * Override compareTo method to compare item
     // * name 
     // * @param newBalance  The new name.
     // */     
     // @Override
    // public int compareTo(Item other) {
        // return this.getName().compareTo(other.getName());
    // }
}

