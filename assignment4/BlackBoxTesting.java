package assignment4;

import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

//The following tests verify the computation of the driver class
//Because it is black box testing, we replicate the driver implementation (what the user would have access to)
//Ie. the tests will NOT assume anything about the internals of the program (ex. input validity checking, words in dictionary, etc.)

public class BlackBoxTesting {

	Assign4Driver Driver;
	ArrayList<String> wordDictionary = new ArrayList<String>();

	protected void setUp() throws Exception {
		Dictionary wordDict = new Dictionary();
		wordDictionary = wordDict.getDictionary("src/A4-words.txt");

	}

	@Test
	/* Test For No Results between angels and devils */
	public void noResult() throws Exception {

		setUp();
		Assignment4Interface wordLadderSolver = new WordLadderSolver();
		wordLadderSolver.setDictionary(wordDictionary);
		List<String> result = wordLadderSolver.computeLadder("angel", "devil");
		assertNull(result);
	}

	@Test
	/* Test between known true results */
	public void trueResult() throws Exception {
		setUp();
		Assignment4Interface wordLadderSolver = new WordLadderSolver();
		wordLadderSolver.setDictionary(wordDictionary);
		List<String> result = wordLadderSolver.computeLadder("heads", "tails");
		boolean correct = wordLadderSolver.validateResult("heads", "tails", result);
		Assign4Driver.printResult(result);
		assertTrue(correct);

	}

}
