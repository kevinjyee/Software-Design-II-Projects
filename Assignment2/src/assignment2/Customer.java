/*
 * Kevin Yee 
 * kjy252 
 */
package assignment2;

public class Customer {

	protected String Name;
	protected int Unique_ID;
	protected String Address;
	protected CheckingAccount Checking;
	protected SavingsAccount Savings;
	protected SavingsAccount Loans;
	protected SavingsAccount Auto;

//constructors

	
	public Customer(String Name, int ID, String Address)
	{
		this.Name = Name;
		this.Unique_ID = ID;
		this.Address = Address;
		this.Checking = new CheckingAccount("C",this);
		this.Savings = new SavingsAccount("S",this);
		this.Loans = new SavingsAccount("L",this);
		this.Auto = new SavingsAccount("A",this);
	}

	
//Modifers
	
	
	public String getName()
	{
		return this.Name;
	}
	
	public String getAddress()
	{
		return this.Address;
	}
	
	public int getID()
	{
		return this.Unique_ID;
	}
	
	public void setName(String Name)
	{
		this.Name = Name;
	}
	
	public void setID(int ID)
	{
		this.Unique_ID = ID;
	}
	
	public void setAddress(String Address)
	{
		this.Address = Address;
	}
	
//bankAccounts 
	
	public CheckingAccount getCheckings()
	{
		return Checking;
	}
	public SavingsAccount getSavings()
	{
		return Savings;
	}
	
	public SavingsAccount getLoans()
	{
		return Loans;
	}
	
	public SavingsAccount getAuto()
	{
		return Auto;
	}
	
	
//Transfer
	
	public void Transfer(BankAccount A, BankAccount B, Double amount)
	{
		if(A.getAccountNumber() == B.getAccountNumber()){
			
			System.out.println("Error, cannot transfer between the same account");
			return;
		}
		if(amount <= A.getBalance())
		{
			A.setBalance(A.getBalance() - amount);
			B.setBalance(B.getBalance()+amount);
			System.out.println("Transfer Complete");
		}
		else{
			System.out.println("Sorry, there is not enough money in this account to transfer");
		}
	}
}
