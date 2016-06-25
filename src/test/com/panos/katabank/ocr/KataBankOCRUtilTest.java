package com.panos.katabank.ocr;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.panos.katabank.ocr.exceptions.MalformedAccountsFileException;
import com.panos.katabank.ocr.exceptions.MalformedNumberException;

/**
 * The test class for KataBankOCRUtil
 * 
 * @author panos
 *
 */
public class KataBankOCRUtilTest {

	/**
	 * The list of the main test input
	 */
	private static final List<String> INPUT_LINES = new ArrayList<String>();
	
	/**
	 * The expected accounts in the results
	 */
	private final String ACCOUNT_1 = "007654321";
	private final String ACCOUNT_2 = "869869823";
	
	/**
	 * This method will prepare the test data for most of the tests
	 */
	@BeforeClass
	public static void prepare() {
		INPUT_LINES.add(" _  _  _  _  _     _  _    ");
		INPUT_LINES.add("| || |  ||_ |_ |_| _| _|  |");
		INPUT_LINES.add("|_||_|  ||_| _|  | _||_   |");
		INPUT_LINES.add("");
		INPUT_LINES.add(" _  _  _  _  _  _  _  _  _ ");
		INPUT_LINES.add("|_||_ |_||_||_ |_||_| _| _|");
		INPUT_LINES.add("|_||_|  ||_||_|  ||_||_  _|");
		INPUT_LINES.add("");
	}
	
	/**
	 * Will test the normal operation of the utility class
	 * @throws MalformedAccountsFileException
	 * @throws MalformedNumberException
	 */
	@Test
	public void testGetAccountNumbers () throws MalformedAccountsFileException, MalformedNumberException {
		List<String> accounts = KataBankOCRUtil.getAccountNumbers(INPUT_LINES);
		
		Assert.assertNotNull(accounts);
		Assert.assertEquals(INPUT_LINES.size() / KataBankOCRUtil.LINES_PER_NUMBER, accounts.size());
		
		String account1 = accounts.get(0);
		Assert.assertEquals(ACCOUNT_1, account1);
		
		String account2 = accounts.get(1);
		Assert.assertEquals(ACCOUNT_2, account2);
	}
	
	/**
	 * Test the empty input
	 * @throws MalformedAccountsFileException
	 * @throws MalformedNumberException
	 */
	@Test(expected=MalformedAccountsFileException.class)
	public void testGetAccountNumbersMalformedFileEmpty() throws MalformedAccountsFileException, MalformedNumberException {		
		KataBankOCRUtil.getAccountNumbers(new ArrayList<String>());		
	}
	
	/**
	 * Test the wrong number of input lines
	 * @throws MalformedAccountsFileException
	 * @throws MalformedNumberException
	 */
	@Test(expected=MalformedAccountsFileException.class)
	public void testGetAccountNumbersMalformedFileLinesCount() throws MalformedAccountsFileException, MalformedNumberException {
		List<String> malformedInputLines = new ArrayList<String>();
		malformedInputLines.add(" _  _  _  _  _     _  _    ");
		malformedInputLines.add("| || |  ||_ |_ |_| _| _|  |");
		malformedInputLines.add("|_||_|  ||_| _|  | _||_   |");
		malformedInputLines.add("");
		malformedInputLines.add(" _  _  _  _  _  _  _  _  _ ");
		malformedInputLines.add("|_||_ |_||_||_ |_||_| _| _|");
		malformedInputLines.add("|_||_|  ||_||_|  ||_||_  _|");
		
		KataBankOCRUtil.getAccountNumbers(malformedInputLines);		
	}
	
	/**
	 * Test the non empty 4th line
	 * @throws MalformedAccountsFileException
	 * @throws MalformedNumberException
	 */
	@Test(expected=MalformedAccountsFileException.class)
	public void testGetAccountNumbersMalformedFileNonEmptyLine() throws MalformedAccountsFileException, MalformedNumberException {
		List<String> malformedInputLines = new ArrayList<String>();
		malformedInputLines.add(" _  _  _  _  _     _  _    ");
		malformedInputLines.add("| || |  ||_ |_ |_| _| _|  |");
		malformedInputLines.add("|_||_|  ||_| _|  | _||_   |");
		malformedInputLines.add("abcd");
		
		KataBankOCRUtil.getAccountNumbers(malformedInputLines);		
	}
	
	/**
	 * Test with a malformed number
	 * @throws MalformedAccountsFileException
	 * @throws MalformedNumberException
	 */
	@Test(expected=MalformedNumberException.class)
	public void testGetAccountNumbersMalformedNumber() throws MalformedAccountsFileException, MalformedNumberException {
		List<String> malformedInputLines = new ArrayList<String>();
		malformedInputLines.add(" _  _  _  _  _    __  _    ");
		malformedInputLines.add("| || |  ||_ |_ |_||_ _|  |");
		malformedInputLines.add("|_||_|  ||_| _|  | _||_   |");
		malformedInputLines.add("");
		
		KataBankOCRUtil.getAccountNumbers(malformedInputLines);		
	}
}
