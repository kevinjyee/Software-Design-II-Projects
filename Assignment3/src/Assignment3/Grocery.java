package Assignment3;

public class Grocery extends Item {
	
	protected boolean perishable;
    
    public Grocery(String name, double price, int quantity, int weight, boolean perishable)
	{
		super(name, price, quantity, weight);
		this.perishable = perishable;		
	}
	
	// override calculatePrice() if necessary; Implement print methods as necessary	
	// Only re-implement stuff you cannot get from the superclass (Item)
    
    double calculatePrice () 
	{
		// No sales Tax
                
        // Shipping cost 
        double shipping_cost = 20*(this.weight); 
        if(this.perishable){
            shipping_cost = shipping_cost*(1.20);
        }
        
        // Final price for the total quantity of item 
        double final_price = quantity * (shipping_cost + price);
        
		// Insert price calculation here
		return final_price;
	}
	
    public boolean getPerishable(){
        return perishable;
    }
    
    void printItemAttributes() 
	{   
        // String for attributes
        String output_name = "Item: " + this.name + "\n";
        String output_price = "Price: $" + String.format("%1$,.2f", this.price) + "\n";
        String output_quantity = "Quantity: " + this.quantity + "\n";;
        String output_weight = "Weight: " + (this.weight*this.quantity) + "\n";
        String output_shipping = "Shipping: "; 
        if(perishable) output_shipping += ("Perishable");
        else output_shipping += ("Non-perishable");
        
		//Print all applicable attributes of this class
        System.out.println(output_name + output_price + output_quantity + output_weight + output_shipping);
	}
}