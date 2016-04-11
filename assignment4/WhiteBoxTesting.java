package assignment4;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

// The following tests verify the computation of the wordLadderSolver class
// Because it is white box testing, we don't replicate the driver implementation (which has some error handling)
// Ie. the tests assume they are valid words, and determine if a ladder exists

public class WhiteBoxTesting {
	Assign4Driver Driver;
	ArrayList<String> wordDictionary = new ArrayList<String>();

	protected void setUp() throws Exception {
		Dictionary wordDict = new Dictionary();
		wordDictionary = wordDict.getDictionary("A4-words.txt");

	}

	@Test
	/*
	 * Test For No Results between words where one or more is the empty string
	 */
	public void testforNull() throws Exception {

		String[] start = { "", "", "heads" };
		String[] end = { "", "tails", "" };
		Assignment4Interface wordLadderSolver = new WordLadderSolver();
		wordLadderSolver.setDictionary(wordDictionary);

		for (int i = 0; i < 3; i++) {
			List<String> result = wordLadderSolver.computeLadder(start[i], end[i]);
			assertNull(result);
		}

	}

	@Test
	public void testforShortWords() throws Exception {

		String[] start = { "abc", "123", "che" };
		String[] end = { "sho", "tais", "nop" };
		Assignment4Interface wordLadderSolver = new WordLadderSolver();
		wordLadderSolver.setDictionary(wordDictionary);

		for (int i = 0; i < 3; i++) {
			List<String> result = wordLadderSolver.computeLadder(start[i], end[i]);
			assertNull(result);
		}

	}

	@Test
	public void testforLongWords() throws Exception {

		String[] start = { "abasfsadfasfc", "12asdfdsf3", "cheasdfsfd" };
		String[] end = { "sasdfsfho", "taiasdfsdfs", "noasdfsfp" };
		Assignment4Interface wordLadderSolver = new WordLadderSolver();
		wordLadderSolver.setDictionary(wordDictionary);

		for (int i = 0; i < 3; i++) {
			List<String> result = wordLadderSolver.computeLadder(start[i], end[i]);
			assertNull(result);
		}

	}

	@Test
	public void testforInvalidChars() throws Exception {

		String[] start = { "a@12", "**091", "&&&&&" };
		String[] end = { "/n/n/", "he!!o", "1337" };
		Assignment4Interface wordLadderSolver = new WordLadderSolver();
		wordLadderSolver.setDictionary(wordDictionary);

		for (int i = 0; i < 3; i++) {
			List<String> result = wordLadderSolver.computeLadder(start[i], end[i]);
			assertNull(result);
		}

	}

}
