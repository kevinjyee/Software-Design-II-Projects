package Assignment3;

import java.util.ArrayList;
import java.util.Arrays;

public class Electronics extends Item 
{
	
	 public static String[] STATES = new String[] {"al", "ak", "as", "az", "ar", "ca", "co", "ct", "de", "dc", "fm", "fl", "ga", "gu", "hi", "id", "il", "in", "ia", "ks", "ky", "la", "me", "mh", "md", "ma", "mi", "mn", "ms", "mo", "mt", "ne", "nv", "nh", "nj", "nm", "ny", "nc", "nd", "mp", "oh", "ok", "or", "pw", "pa", "pr", "ri", "sc", "sd", "tn", "tx", "ut", "vt", "vi", "va", "wa", "wv", "wi", "wy"};
	 public static String[] EXSTATES = new String[] {"tx","nm","va","az","ak"};
	 public static ArrayList<String > ALL_STATES = new ArrayList(Arrays.asList(STATES));
	 public static ArrayList<String> EXCEPTION_STATES = new ArrayList(Arrays.asList(EXSTATES));
	 
	 
    protected boolean fragile; 
<<<<<<< HEAD
    protected String state;
=======
    
>>>>>>> 58135eb1fca000355501033e9876c4f739594b32
	public Electronics(String name, double price, int quantity, int weight, boolean fragile, String state)
	{
		super(name, price, quantity, weight);
	    this.fragile = fragile;
    }
    
    double calculatePrice () 
	{
		// Sales Tax
        double tax_cost;
<<<<<<< HEAD
        if(EXCEPTION_STATES.contains(state)){
=======
        if(EXSTATES.contains(state)){
>>>>>>> 58135eb1fca000355501033e9876c4f739594b32
            tax_cost = 0;
        } else{
            tax_cost = this.price * (.1); // 10% tax
        }
                
        // Shipping cost 
        double shipping_cost = (20*(this.weight)); 
        if(this.fragile){
            shipping_cost = shipping_cost*(1.20);
        }
        
        // Final price for the total quantity of item 
        double final_price = quantity * (tax_cost + shipping_cost + price);
        
		// Insert price calculation here
		return final_price;
	}

    void printItemAttributes() 
	{   
        // String for attributes
        String output_name = "Item: " + this.name + "\n";
        String output_price = "Price: $" + String.format("%1$,.2f", this.price) + "\n";
        String output_quantity = "Quantity: " + this.quantity + "\n";;
        String output_weight = "Weight: " + (this.weight*this.quantity) + "\n";
        String output_shipping = "Shipping: "; 
        if(fragile) output_shipping += ("Fragile");
        else output_shipping += ("Non-fragile");
        output_name += " (" + state + ")\n";
        
		//Print all applicable attributes of this class
        System.out.println(output_name + output_price + output_quantity + output_weight + output_shipping);
	}
    
    public boolean getFragile(){
        return fragile;
    }	
    
    public void setFragile(boolean fragile){
        this.fragile = fragile;
    }	

}
