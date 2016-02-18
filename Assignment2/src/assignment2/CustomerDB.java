/*
 * Kevin Yee 
 * kjy252 
 */
package assignment2;

import java.util.HashMap;

public class CustomerDB {

protected HashMap<Integer,Customer> customers;

public CustomerDB(){
	customers = new HashMap<>();
}


public void put(Customer client)
{
	customers.put(client.Unique_ID, client);
}

public Customer get(Integer ID)
{
	return customers.get(ID);
}

}
