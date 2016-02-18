package assignment2;

public class SavingsAccount extends BankAccount {
	
	protected final double MIN_BALANCE = 1000.00;
	protected final double INTEREST = 1.04;
	//constructors
    /**
     * Create an account with an initial balance.
     * @param initialBalance     The initial balance of the account
     */
    public SavingsAccount(double initialBalance)
    {
    	super(initialBalance);
    }
    
    /**
     * Create an account with initial parameters.
     * @param acct               The account number
     * @param owner              The owner of the account
     * @param initBalance        The initial balance of the account
     */
    public SavingsAccount(String acct, Customer owner)
    {
        super(acct, owner); 
    }
    
    
    public double getInterest()
    {
    	if(this.balance > MIN_BALANCE){
    		this.balance = INTEREST * this.balance;
    	
    	return this.balance;
    	}
    	else{
    		System.out.println("Sorry, no interest was added. The minimum balance for interest to accumulate is $" + MIN_BALANCE);
    		return this.balance;
    	}
    }
    
}
