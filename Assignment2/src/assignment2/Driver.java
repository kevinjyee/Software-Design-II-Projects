/* Solves EE422 Assignment #2
 * Kevin Yee 
 * kjy252 
 */
package assignment2;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import javax.swing.JOptionPane;

public class Driver {
	
	private static final String regDOUBLE =  "^\\d+(\\.\\d+)?$";
	private static String pID = "0123456789";
	private static String pTRANSACTION = "DWITG";
	private static String pACCOUNTS = "CSLA";
	
	public static CustomerDB DB;
	public static HashMap<Integer,Boolean> Clients = new HashMap<>();
	
	public static void main(String[] args)
	{
		
		
		DB = new CustomerDB();
		
		Customer Customer0 = new Customer("Liam",0,"Whitis");
		Customer Customer1 = new Customer("Noah",1,"Villas");
		Customer Customer2 = new Customer("Ethan",2,"JEast");
		Customer Customer3 = new Customer("Mason",3,"JWest");
		Customer Customer4 = new Customer("Lucas",4,"Duren");
		Customer Customer5 = new Customer("Logan",5,"SanJac");
		Customer Customer6 = new Customer("Oliver",6,"Blanton");
		Customer Customer7 = new Customer("Jackson",7,"Redgate");
		Customer Customer8 = new Customer("Aiden",8,"Whitis");
		Customer Customer9 = new Customer("Jacob",9,"Whitis");
		
		DB.put(Customer0);
		DB.put(Customer1);
		DB.put(Customer2);
		DB.put(Customer3);
		DB.put(Customer4);
		DB.put(Customer5);
		DB.put(Customer6);
		DB.put(Customer7);
		DB.put(Customer8);
		DB.put(Customer9);
		
		int output = JOptionPane.NO_OPTION;
		
		while(output == JOptionPane.NO_OPTION){
		JOptionPane Input = new JOptionPane();
		String Transaction = Input.showInputDialog("Please Enter Transaction");
		if(Transaction == null){
			System.out.println("You have Canceled your Transaction, Thank You for visiting our Bank");
			break;}
		String completeTransaction = processTransaction(Transaction.toUpperCase());
		System.out.println(completeTransaction);
	    output = Input.showConfirmDialog(null,"Done with Transaction?");
	    if(output == JOptionPane.CANCEL_OPTION){System.out.println("Thank You for visiting our Bank");}
		} 
		printSummary();
		}
	
	
	public static String processTransaction(String Transaction)
	{
		Integer CustomerID;
		String TransactionType;
		Double Amount;
		String InterestAccount;
		String AccountType1;
		String AccountType2;
		
		String[] tokens = new String[5];
		tokens = Transaction.split("\\s+");
		
		if(tokens.length < 3){return "Error, not enough arguments in Transaction. Please Try again";}
		if(tokens.length > 5){
			return "Error, too many arguments in Transcation. Please Try again";}
		if(!pID.contains(""+tokens[0].charAt(0)) || tokens[0].length() > 1){return "Error in ID Number";}
		else{
			CustomerID = Integer.parseInt(tokens[0]);
			Clients.put(CustomerID,true);
		}
		if(!pTRANSACTION.contains(tokens[1]) || tokens[1].length() > 1){return "Error in Transaction Type";}
		else{TransactionType = tokens[1];}
		
		Customer customer = DB.get(CustomerID);
		
		if(TransactionType.equals("D"))
		{
			return Deposit(customer,tokens);
		}
		else if(TransactionType.equals("W"))
		{
			return Withdraw(customer,tokens);
		}
		else if(TransactionType.equals("I"))
		{
			return Interest(customer,tokens);
		}
		else if(TransactionType.equals("T"))
		{
			return Transfer(customer,tokens);
		}
		else if(TransactionType.equals("G"))
		{
			return getBalance(customer,tokens);
		}
		else{
			return "Invalid Transaction Type";
		}
	}
		
		
		public static String Deposit(Customer customer, String[] tokens)
		{
			if(tokens.length > 4){return "ERROR: Input String too Long for calculating Deposit";}
			if(!tokens[2].matches(regDOUBLE)){return "ERROR: Please enter amount in 0.00 format";}
			Double depositAmount = Double.parseDouble(tokens[2]);
			String Account = tokens[3];
			
			if(Account.equals("C"))
			{
				CheckingAccount cAccount = customer.getCheckings();
				cAccount.deposit(depositAmount);
			}
			
			else if(Account.equals("S"))
			{
				SavingsAccount sAccount = customer.getSavings();
				sAccount.deposit(depositAmount);
			}
			
			else if(Account.equals("L"))
			{
				SavingsAccount lAccount = customer.getLoans();
				lAccount.deposit(depositAmount);
			}
			
			else if(Account.equals("A"))
			{
				SavingsAccount aAccount = customer.getAuto();
				aAccount.deposit(depositAmount);
			}
			
			else{
			return "Invalid Accout Type";
			}
			return "Deposit to Customer " + customer.getID() + " Success";
		}
		
		public static String Withdraw(Customer customer, String[] tokens)
		{
			
			if(tokens.length != 4){return "ERROR: Input String not the correct length for calculating Withdrawal";}
			if(!tokens[2].matches(regDOUBLE)){return "ERROR: Please enter amount in 0.00 format";}
			Double withdrawAmount = Double.parseDouble(tokens[2]);
			String Account = tokens[3];
			
			if(Account.equals("C"))
			{
				CheckingAccount cAccount = customer.getCheckings();
				if(!cAccount.withdrawChecking(withdrawAmount)){
					System.out.println("Transcation Failed. You do not have enough money in Checkings nor Primary Savings. No Money was withdrawn");
				};
			}
			
			else if(Account.equals("S"))
			{
				SavingsAccount sAccount = customer.getSavings();
				sAccount.withdraw(withdrawAmount);
			}
			
			else if(Account.equals("L"))
			{
				SavingsAccount lAccount = customer.getLoans();
				lAccount.withdraw(withdrawAmount);
			}
			
			else if(Account.equals("A"))
			{
				SavingsAccount aAccount = customer.getLoans();
				aAccount.withdraw(withdrawAmount);
			}
			else{
			return "Invalid Account Type";
			}
			return "Withdrawal Transaction for Customer " + customer.getID() + " Complete";
		}
		
		public static String Interest(Customer customer, String[] tokens)
		{
			if(tokens.length != 3){return "ERROR: Input String not correct length for calculating Interest";}
			String Account = tokens[2];
			
			if(Account.equals("C"))
			{
				System.out.println("Sorry Checking Balances do not accumulate interest");
				System.out.format("Customer " + customer.getID() + " \'s Checking Balance: - $%.2f%n",customer.Checking.getBalance());
			}
			
			else if(Account.equals("S"))
			{
		
			System.out.format("You current balance in Savings Account with interest is $%.2f%n", customer.getSavings().getInterest());
			}
			
			else if(Account.equals("L"))
			{
			System.out.format("You current balance in Loans Account with interest is $%.2f%n" , customer.getLoans().getInterest());
			}
			
			else if(Account.equals("A"))
			{
			System.out.format("You current balance in Auto Account with interest is $%.2f%n"  , customer.getLoans().getInterest());
			}
			else{
			return "Invalid Account Type";
			}
			return "";
		}
		
		public static BankAccount getAccountType(Customer customer, String Account)
		{
		
			if(Account.equals("C"))
			{
				return customer.getCheckings();
			}
			
			else if(Account.equals("S"))
			{
		
				return customer.getSavings();
			}
			
			else if(Account.equals("L"))
			{
				return customer.getLoans();
			}
			
			else if(Account.equals("A"))
			{
				return customer.getAuto();
			}
			
				return null;
			
		}
		
		public static String Transfer(Customer customer, String[] tokens)
		{
			if(tokens.length != 5){return "ERROR: Input String does not meet required length";}
			if(!pACCOUNTS.contains(""+tokens[3].charAt(0)) || tokens[3].length() > 1){return "Error in Input, Transfer Account 1";}
			if(!pACCOUNTS.contains(""+tokens[4].charAt(0)) || tokens[4].length() > 1){return "Error in Input, Transfer Account 2";}
			Double amount = Double.parseDouble(tokens[2]);
			String Account1 = tokens[3];
			String Account2 = tokens[4];
			BankAccount BankAccount1 = getAccountType(customer,Account1);
			BankAccount BankAccount2 = getAccountType(customer,Account2);
			
			customer.Transfer(BankAccount1, BankAccount2, amount);
			
			
			return "";
		}
		
		public static String getBalance(Customer customer, String[] tokens)
		{
			if(tokens.length != 3){return "ERROR: Input String not the right length for Getting Balance";}
			String Account = tokens[2];
			
			if(Account.equals("C"))
			{
				CheckingAccount cAccount = customer.getCheckings();
				System.out.format("Customer " + customer.getID() + " \'s Checking Balance: - $%.2f%n",cAccount.getBalance());
				
			}
			
			else if(Account.equals("S"))
			{
				SavingsAccount sAccount = customer.getSavings();
				System.out.format("Customer " + customer.getID() + " \'s Primary Savings Balance: - $%.2f%n",sAccount.getBalance());
			}
			
			else if(Account.equals("L"))
			{
				SavingsAccount lAccount = customer.getLoans();
				System.out.format("Customer " + customer.getID() + " \'s Loans Account Balance: - $%.2f%n",lAccount.getBalance());
			}
			
			else if(Account.equals("A"))
			{
				SavingsAccount aAccount = customer.getAuto();
				System.out.format("Customer " + customer.getID() + " \'s Auto Pay Balance: - $%.2f%n",aAccount.getBalance());
			}
			else{
			
			return "Invalid Accout Type";
			}
			return "";
		}
		
	
		
		public static void printSummary()
		{
			if(Clients.size() == 0){System.out.println("There has been no customers in this Bank");
			
			}
			for(Map.Entry<Integer,Boolean> clients: Clients.entrySet()){
				System.out.println("\nThe following are the final account statements:\n");
		    	
				System.out.println("\tCustomer Name: " + DB.get(clients.getKey()).getName() + ", Address: " + 
				DB.get(clients.getKey()).getAddress() + ", Customer ID: " + 
				DB.get(clients.getKey()).getID());		    	
				
				System.out.println("\tCustomer " + clients.getKey() + "\'s Checking Account:");
		    	
		    	System.out.format("\t\tBalance - $%.2f%n", DB.get(clients.getKey()).getCheckings().getBalance());
		    	System.out.println("\tCustomer " + clients.getKey() + "\'s Savings Account:");
		    	System.out.format("\t\tBalance - $%.2f%n", DB.get(clients.getKey()).getSavings().getBalance());
		    	System.out.println("\tCustomer " + clients.getKey() + "\'s Student Loans Account:");
		    	System.out.format("\t\tBalance - $%.2f%n",DB.get(clients.getKey()).getLoans().getBalance());
		    	System.out.println("\tCustomer " + clients.getKey() + "\'s Auto Loans Account:");
		    	System.out.format("\t\tBalance - $%.2f%n\n", DB.get(clients.getKey()).getAuto().getBalance());
			}
	    	
		}

}
