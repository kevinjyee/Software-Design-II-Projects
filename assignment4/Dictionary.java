/*
 Dictionary Class
 Solves EE422C programming assignment #4
 @author Hari (hk8663), Kevin Yee (kjy252)
 @version 1.01 2016-03-03
 */

package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Dictionary {

	private ArrayList<String> Dictionary;

 
	/*Constructor for Dictionary*/
	public Dictionary()
	{
		this.Dictionary = new ArrayList<String>();
	}
	
	
	/*Method Returning ArrayList*/
	
	public  ArrayList<String> getDictionary (String filename) throws Exception 
    {
		BufferedReader reader;
        try 
        {
            // Read file 
            FileReader freader = new FileReader(filename);
            reader = new BufferedReader(freader);
            
            // Iterate over and process each line of file
            for (String s = reader.readLine(); s != null; s = reader.readLine()) 
            {
            	if(!s.startsWith("*"))
				{
					this.Dictionary.add(s.substring(0,5)); // add words to dictionary
				};             
            }
        } 
        catch (FileNotFoundException e) 
        {
            System.err.println ("Error: File not found. Exiting...");
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) 
        {
            System.err.println ("Error: IO exception. Exiting...");
            e.printStackTrace();
            System.exit(-1);
        }
      return this.Dictionary;
    }
}
