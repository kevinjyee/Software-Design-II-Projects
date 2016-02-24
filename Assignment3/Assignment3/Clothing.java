/**
  Clothing Class
  Solves EE422C programming assignment #3
  @author Kevin Yee (kjy252), Alvin Tung (ayt243)
  @version 1.00 2016-02-023
 */
 
package Assignment3;

public class Clothing extends Item 
{   
// Constructors    
    /**
     * Create a clothing item with a name, price, initial quantity, and weight
     * @param name     The name of the clothing
     * @param price    The price of the clothing
     * @param quantity The initial quantity of the clothing 
     * @param weight   The weight of the clothing
     */
    public Clothing(String name, double price, int quantity, int weight)
    {
        super(name, price, quantity, weight);
    }

    /**
     * Prints the instance variable attributes in a readable way 
     */         
    void printItemAttributes() 
    {   
        // Create String for attributes
        String output_name = "Name: " + this.name + "\n";
        String output_type = "Item Type: Clothing" + "\n";                
        String output_price = "Price: $" + String.format("%1$,.2f", this.price) + "\n";
        String output_quantity = "Quantity: " + this.quantity + "\n";;
        String output_weight = "Weight: " + (this.weight) + "\n";
        String output_shipping = "Shipping: Standard";
        
        //Print all attributes of this class
        System.out.println(output_name + output_type + output_price + output_quantity + output_weight + output_shipping);
    }
}
