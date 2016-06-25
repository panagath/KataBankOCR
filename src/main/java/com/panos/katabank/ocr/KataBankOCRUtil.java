package com.panos.katabank.ocr;

import java.util.LinkedList;
import java.util.List;

import com.panos.katabank.ocr.exceptions.MalformedAccountsFileException;
import com.panos.katabank.ocr.exceptions.MalformedNumberException;

/**
 * This is the util class to handle reading account numbers from a
 * file that represents numbers in a "digital" format using underscores
 * and bars. Each numbers is represented in 3 lines * 3 characters in the
 * input file plus an empty line after each account number.
 *  
 * @author panos
 *
 */
public class KataBankOCRUtil {
	
	/**
	 * Lines per account number in the input file
	 */
	public static final Integer LINES_PER_NUMBER = 4;
	
	/**
	 * Count of numbers an account consists of
	 */
	public static final Integer ACCOUNT_NUMBER_SIZE = 9;
	
	/**
	 * The offsets of each line inside the input block
	 */
	private static final Integer TOP_LINE_OFFSET    = 0;
	private static final Integer MEDIUM_LINE_OFFSET = 1;
	private static final Integer BOTTOM_LINE_OFFSET = 2;
	private static final Integer EMPTY_LINE_OFFSET  = 3;

	
	/**
	 * This method will return the account numbers extracted from the input file
	 * @param inputLines - A list with the lines read from the input file
	 * @return - A list with the account numbers. The result is a list of String and not int as the account may start with 0
	 * @throws MalformedAccountsFileException - Thrown when the input from the file is malformed
	 * @throws MalformedNumberException - Thrown when the input does not represent a number
	 */
	public static List<String> getAccountNumbers ( final List<String> inputLines ) throws MalformedAccountsFileException, MalformedNumberException {
		List<String> accounts = new LinkedList<String>();
		
		// Validate the input
		validateInput(inputLines);
		
		// Loop through all the accounts blocks
		for ( int line = 0; line < inputLines.size(); line += LINES_PER_NUMBER ) {
			
			//Get block lines & validate
			int topLinePos = line + TOP_LINE_OFFSET;
			String topLine = inputLines.get(topLinePos);
			validateLineInput(topLine, topLinePos);
			
			int mediumLinePos = line + MEDIUM_LINE_OFFSET;
			String middleLine = inputLines.get(mediumLinePos);
			validateLineInput(middleLine, mediumLinePos);
			
			int bottomLinePos = line + BOTTOM_LINE_OFFSET;
			String bottomLine = inputLines.get(bottomLinePos);
			validateLineInput(bottomLine, bottomLinePos);
			
			int emptyLinePos = line + EMPTY_LINE_OFFSET;
			String lineEmp = inputLines.get(emptyLinePos);
			validateLineInput(lineEmp, emptyLinePos);
			
			//Get each number
			String account = "";
			for ( int j = 0 ; j < ACCOUNT_NUMBER_SIZE ; j++ ) {
				Integer number = BarsDigitHolder.getNumber(topLine, middleLine, bottomLine, j, topLinePos); 
				account += number;
			}
			
			//Add the number in the list of accounts
			accounts.add(account);
		}
		
		
		return accounts;
	}
	
	/**
	 * This method is used to validate the input lines of the accounts file
	 * @param inputLines - A list with the lines read from the input file
	 * @return true if the input is valid
	 */
	private static void validateInput ( final List<String> inputLines ) throws MalformedAccountsFileException {
		
		//If the input is empty
		if ( inputLines == null || inputLines.size() == 0 ) {
			throw new MalformedAccountsFileException("Empty input");
		//The count of lines should be a multiple to the lines count per digit	
		} else if ( inputLines.size() % LINES_PER_NUMBER != 0 ) {
			throw new MalformedAccountsFileException("Wrong number of input lines");
		}
		
	}
	
	/**
	 * This method validates a line
	 * @param line - the line to validate
	 * @param digitLinePos -  The position of the line in the input list
	 * @throws MalformedAccountsFileException - Thrown when a line is malformed
	 */
	private static void validateLineInput ( String line, int digitLinePos ) throws MalformedAccountsFileException {
		//If the line is empty and is not the last empty line
		int lineOffset = digitLinePos % LINES_PER_NUMBER;
		if ( line.isEmpty() && ( lineOffset != LINES_PER_NUMBER - 1) ) {
			throw new MalformedAccountsFileException("Invalid count of characters in line: " + digitLinePos );
		//If the line is the last of the digit, it must be empty	
		} else if ( !line.trim().isEmpty() && ( lineOffset == LINES_PER_NUMBER - 1) ) {
			throw new MalformedAccountsFileException("Line " + digitLinePos + " should be empty");
		}
	}
	
}
