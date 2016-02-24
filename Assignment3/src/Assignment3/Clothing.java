/**
  Clothing Class
  Solves EE422C programming assignment #3
  @author Kevin Yee (ky), Alvin Tung (ayt243)
  @version 1.00 2016-02-023
 */
 
package Assignment3;

public class Clothing extends Item 
{   
// Constructors    
    /**
     * Create a clothing item with a name, price, initial quantity, and weight
     * @param name     The name of the clothing
     * @param price    The price of the clothing
     * @param quantity The initial quantity of the clothing 
     * @param weight   The weight of the clothing
     */
    public Clothing(String name, double price, int quantity, int weight)
    {
        super(name, price, quantity, weight);
    }
}
