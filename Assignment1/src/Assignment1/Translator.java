/*English to PigLatin
 *Solves EE422C programming assignment #1 
 *@author Kevin Yee - kjy252
 *@version 1.01 2016-01-27
 */

package Assignment1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Translator 
{
	
	public static void main (String args[]) 
	{ 
		if (args.length != 1) 
		{
			System.err.println ("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
		processLinesInFile (args[0]);
		
	}

	 
	
	/******************************************************************************
	* Method Name: processLinesInFile                                             *
	* Purpose: Opens the file specified in String filename, reads each line in it *
	*          Invokes translate () on each line in the file, and prints out the  *
	*          translated piglatin string.                                        *
	* Returns: None                                                               *
	******************************************************************************/
	public static void processLinesInFile (String filename) 
	{ 

		Translator translator = new Translator(); 
		try 
		{
			FileReader freader = new FileReader(filename);
			BufferedReader reader = new BufferedReader(freader);
			
			for (String s = reader.readLine(); s != null; s = reader.readLine()) 
			{
				String pigLatin = translator.translate(s);
				System.out.println(pigLatin);
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
	}
	/******************************************************************************
	* Method Name: containsSymbol                                                 *
	* Purpose: Determines if given string has symbols                             *
	*                                                                             *
	* Returns: Boolean Value                                                      *
	*                                                                             *
	******************************************************************************/
	public boolean containsSymbol(String input)
	{
		
		char Hyphen = '-';
		char apostroph = '\'';
		
		String punctuations = "\"(...):;\'.!?,";
		
		for(int i =0; i<input.length()-1; i++)
		{
			if(!Character.isLetter(input.charAt(i))){
				if(input.charAt(i) != apostroph && input.charAt(i) != Hyphen && !punctuations.contains(""+input.charAt(i) ))
				{			
					/*punctuations, hyphens, and apostrophs do not count as "symbols" 
					 * return true if these symbols are not found
					 */
					return true; 
				}
					
			}
		}
		return false; // return false if no symbols were found
	}
	/******************************************************************************
	* Method Name: translate                                                      *
	* Purpose: Places all non letters into an output String                       *
	*          Translate letters into piglatin                                    *
	* Returns: String object containing the piglatin translation of               *
	*          String inputString                                                 *
	******************************************************************************/
	
	public String translate (String inputString) 
	{ 
		String punctuations = "\"(...):;.!?,-";
		String outputString = new String();
		String apostroph = "\'";
		int i = 0;
		
		while(i<inputString.length())
		{
			while(!Character.isLetter(inputString.charAt(i)))
			{
				//Puts on noncharacters into outputString
				outputString += inputString.charAt(i);
				i++;
					if(i>=inputString.length()){break;}
			}
				if(i>=inputString.length()){break;}
		
				int begin = i; //find beginning of word
		
			while(inputString.charAt(i) != ' ' && !punctuations.contains("" + inputString.charAt(i)))
			{
				//sees a potential word. Loop til we see a space or punctuation
				i++;
				if(i >= inputString.length()){break;}
				try{
				//try attempts to see if it's the last word (prevents index out of bouds exception)
					if(inputString.charAt(i) == '\'' && inputString.charAt(i+1) == ' '){
					break;
				}
				}catch(IndexOutOfBoundsException e){
					break;
				}
				
			}
				int end = i; //finds end of word
				
		String word = inputString.substring(begin, end); 
		String value = pigLatin(word); // return a "pigLatinzed version of the word" 
		outputString += value; // add this to the end of our output
		
		}
		return outputString;
	}
	
	/******************************************************************************
	* Method Name: pigLatin                                                       *
	* Purpose: Translates string s into a piglatin string                         *
	*                                                                             *
	* Returns: String object containing the piglatin translation of               *
	*          String inputString                                                 *
	******************************************************************************/
	public String pigLatin (String s)
	{
		String latin = "";
		String vowels = "AEIOUaeiou\'";
		
		if(containsSymbol(s)){
			//returns if it contains a sybmol
			latin = latin.concat(s);
			return latin;
		}
		
		for(int i =0; i < s.length(); i++){
			
			if(vowels.contains(""+s.charAt(i)))
			{ //check to see if it begins with a vowel
				while(Character.isLetter(s.charAt(i)) || s.charAt(i) == '\'')
				{
					latin += s.charAt(i);
					i++;
					if(i == s.length()){break;}//break at end of the word
				}
				latin +="yay"; 
			}
			else
			{//not a value, it's a consanant
		
				String consanant = new String();
				while(!vowels.contains(""+s.charAt(i)))
				{//loops until it find a vowel
					
					consanant += s.charAt(i);
					i++;
					
					if(i == s.length() || s.charAt(i) == ' '){break;}// if it's the end of the word break
					if(s.charAt(i) == 'y'){break;} // we count y as a vowel if it's inside the word
				}
					if(i == s.length()){
						latin+= consanant;
						latin+="yay";
						break;
					}
					
				while(Character.isLetter(s.charAt(i)) || s.charAt(i) == '\'')
				{//the value is found, concat the end of the consanants to end
					latin += s.charAt(i);
					i++;
						if(i == s.length())
						{
						break;
					    }
				}
				
				latin+= consanant;
				latin+="ay";
			}
			
		}
		return latin;
	}
	}
	
	
	

