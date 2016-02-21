package Assignment3;

public class Grocery extends Item {
	
	protected boolean perishable;
	public Grocery(String name, double price, int quantity, double weight, boolean perishable)
	{
		super(name, price, quantity, weight);
		this.perishable = perishable;
		
	}
	
	//override calculatePrice() if necessary; Implement print methods as necessary	
	// Only re-implement stuff you cannot get from the superclass (Item)
	
}
