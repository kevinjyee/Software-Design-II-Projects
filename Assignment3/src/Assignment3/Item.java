package Assignment3;

public class Item 
{

	protected String name;
	protected double price;
	protected int quantity;
	protected double weight;
	
	public Item(String name, double price, int quantity, double weight)
	{
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.weight = weight;
	}
	
	float calculatePrice () 
	{
		float final_price = 0;
		// Insert price calculation here
		return final_price;
	}
	

	void printItemAttributes () 
	{
		//Print all applicable attributes of this class
	}

}
