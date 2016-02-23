package Assignment3;

public class Item implements Comparable<Item>
{

	protected String name;
	protected double price;
	protected int quantity;
	protected int weight;

    public Item(String name, double price, int quantity, int weight)
	{
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.weight = weight;
	}
	
	double calculatePrice () 
	{
		// Sales Tax
        double tax_cost = this.price * (.1); // 10% tax
                
        // Shipping cost 
        double shipping_cost = 20*(this.weight); 
        
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
        String output_weight = "Weight: " + (this.weight*this.quantity);
        
		//Print all applicable attributes of this class
        System.out.println(output_name + output_price + output_quantity + output_weight);
	}
    
    public String getName(){
        return name;
    }
    
    public double getPrice(){
        return price;
    }    
 
    public int getQuantity(){
        return quantity;
    }

    public int getWeight(){
        return weight;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setPrice(double price){
        this.price = price;
    }    
 
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public int compareTo(Item other) {
        return this.getName().compareToIgnoreCase(other.getName());
}
}

