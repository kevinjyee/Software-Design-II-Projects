package Assignment3;

public class Electronics extends Item 
{
    protected boolean fragile; 
    
	public Electronics(String name, double price, int quantity, int weight, boolean fragile)
	{
		super(name, price, quantity, weight);
	    this.fragile = fragile;
    }
    
    double calculatePrice () 
	{
		// Sales Tax
        double tax_cost = this.price * (.1); // 10% tax
                
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
        if(fragile) output_shipping += ("Fragile" + "\n");
        else output_shipping += ("Non-fragile" + "\n");
        
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
