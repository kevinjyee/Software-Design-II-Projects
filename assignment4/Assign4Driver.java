/*
 Assign4Driver
 Solves EE422C programming assignment #4
 @author Hari (hk8663), Kevin Yee (kjy252)
 @version 1.01 2016-03-03
 */

package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class Assign4Driver {

	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Error: Incorrect number of command line arguments");
			System.exit(-1);
		}
		processDictionary(args[0]); // args[0] contains dictionary file
		processCommands(args[1]); // args[1] contains test file

	}

	/*
	 * Function: processDictoinary 
	 * ------------------------------------
	 * Puts Dictionary into ArrayList
	 */
	public static void processDictionary(String filename) throws Exception {
		BufferedReader reader = null; // Outside try block to ensure that reader
										// is closed in the finally block
		try {
			// Read file
			// ***Assumes that the dictionary file is in the root directory
			// (workspace)
			FileReader freader = new FileReader(filename);
			reader = new BufferedReader(freader);

			// Iterate over and process each line of file
			for (String s = reader.readLine(); s != null; s = reader.readLine()) {
				if (!s.startsWith("*")) {
					WordLadderSolver.getDictionary().add(s.substring(0, 5)); // add words to dictionary
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			System.err.println("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} finally {
			if (reader != null)
				reader.close();
		}
		return;
	}

	/**
	 * Function: processCommands
	 * ------------------------------
	 *  Process word pairs to solve
	 *  @params filename
	 ***********************************/

	public static void processCommands(String filename) throws Exception {
		BufferedReader reader = null; // Outside try block to ensure that reader is closed in the finally block
		try {
			// Read file
			// ***Assumes that the input file is in the root directory (workspace)
			FileReader freader = new FileReader(filename);
			reader = new BufferedReader(freader);

			// Iterate over and process each line of file
			for (String s = reader.readLine(); s != null; s = reader.readLine()) {
				String[] inputs;
				inputs = s.split("[ ]+");
				// Catches 0 (null/empty line) inputs
				if (inputs.length == 0) {
					System.out.println("For the input words (null) and (null) :\nAt least one of the words (null) and (null) are not legitimate 5-letter words from the dictionary");
					System.out.println("**********");
					continue;
				}
				// Catches one word inputs
				else if (inputs.length == 1) {
					System.out.println("For the input words " + inputs[0] + " and (null) :\nAt least one of the words "
							+ inputs[0] + " and (null) are not legitimate 5-letter words from the dictionary");
					System.out.println("**********");
					continue;
				}
				// Catches two word inputs, where atleast one of them is null/empty
				else if (inputs.length == 2 && (inputs[0].equals("") || inputs[1].equals(""))) {
					System.out.println("For the input words " + inputs[0] + " and " + inputs[1]
							+ ":\nAt least one of the words " + inputs[0] + " and " + inputs[1]
							+ " are not legitimate 5-letter words from the dictionary");
					System.out.println("**********");
					continue;
				}
				// Catches more than two word inputs
				else if (inputs.length > 2) {
					System.out.println("For the input with words " + inputs[0] + " and " + inputs[1]
							+ ":\nThe input is invalid (there are too many input arguments)");
					System.out.println("**********");
					continue;
				}

				// Input is of valid syntax
				solveLadder(inputs); // solves each word ladder
			}
		}

		catch (FileNotFoundException e) {
			System.err.println("Error: File not found. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			System.err.println("Error: IO exception. Exiting...");
			e.printStackTrace();
			System.exit(-1);
		} finally {
			// Close reader/input stream
			if (reader != null)
				reader.close();
		}
		return;
	}
	

	/* Function: solveLadder 
	 * 	Solves the WordLadder
	 * --------------------------------
	 * @param inputs Strings to build ladder from
	 * @return void
	 */
	public static void solveLadder(String[] inputs) {
		Assignment4Interface wordLadderSolver = new WordLadderSolver();

		try {
			// Converts inputs to lowercase (just to make sure)
			String startWord = inputs[0].toLowerCase();
			String endWord = inputs[1].toLowerCase();

			// Checks if the inputs are elements of the dictionary
			if (!WordLadderSolver.getDictionary().contains(startWord)
					|| !WordLadderSolver.getDictionary().contains(endWord)) {
				System.out.println("For the input words " + startWord + " and " + endWord
						+ ":\nAt least one of the words " + startWord + " and " + endWord
						+ " are not legitimate 5-letter words from the dictionary");
				System.out.println("**********");
				return;
			}

			List<String> result = wordLadderSolver.computeLadder(startWord, endWord);

			// If result is not null/empty, a ladder was successfully computed
			// Also checks validity of the non-empty result
			if (result != null && wordLadderSolver.validateResult(inputs[0], inputs[1], result)) {
				printResult(result);
			}

		} catch (NoSuchLadderException e) {
			e.printStackTrace();
		}
	}

	/* Function: printResult 
	 * ------------------------------
	 * Prints Item in Results Array
	 * 
	 * @param result result of Word Ladder
	 * 
	 * @return void
	 ******************************/

	public static void printResult(List<String> result) {
		System.out.println("For the input words " + result.get(0) + " and " + result.get(result.size() - 1)
				+ ", the following word ladder was found:");
		Iterator<String> i = result.iterator();
		while (i.hasNext()) {
			String temp = i.next(); // grab next item
			System.out.println(temp);
		}

		System.out.println("**********");

	}

}
