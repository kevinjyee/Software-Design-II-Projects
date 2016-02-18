/*
 * Kevin Yee 
 * kjy252 
 */
package assignment2;

public class CheckingAccount extends BankAccount {
	
	
	protected final double OVERDRAFT_FEE = 20.0;
	
	//constructors
    /**
     * Create an account with an initial balance.
     * @param initialBalance     The initial balance of the account
     */
    public CheckingAccount(double initialBalance)
    {
    	super(initialBalance);
    }
    
    /**
     * Create an account with initial parameters.
     * @param acct               The account number
     * @param owner              The owner of the account
     * @param initBalance        The initial balance of the account
     */
    public CheckingAccount(String acct, Customer owner)
    {
        super(acct, owner); 
    }
 
    public boolean withdrawChecking(double amount) 
    {  
    	
        if (balance >=  amount){
            balance = balance - amount; 
        	return true;
        }
        else
        {
        System.out.println("You have overdrawn your Checking Account. Attempting to withdraw from primary savings");
         double overcharge;
         
         overcharge = amount - balance + OVERDRAFT_FEE;
         SavingsAccount PrimarySavings = ownersName.getSavings();
         if(overcharge > PrimarySavings.getBalance())
         { 
        	 
         return false;
         }
         else{
        	 
        	 System.out.format("We have withdrawn $%.2f%n from your Checking and $%.2f%n from your Savings\n",balance, overcharge);
        	 PrimarySavings.withdraw(overcharge);
        	balance =0.00;
         }
         
         
        }
        return true;
    }
    
  
}
