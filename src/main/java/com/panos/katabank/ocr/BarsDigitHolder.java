package com.panos.katabank.ocr;

import java.util.HashMap;
import java.util.Map;

import com.panos.katabank.ocr.exceptions.MalformedNumberException;

/**
 * This class contains the mapping of each number to the digital representation
 * in bars & underscores as in the input file
 * 
 * @author panos
 *
 */
public class BarsDigitHolder {

	
	/**
	 * Horizontal characters in each line to represent a number
	 */
	public static final Integer CHARACTERS_PER_NUMBER_IN_LINE = 3;
	
	/**
	 * This map contains as key the BarDigit object and as value the number represented by it
	 */
	public static final Map<BarsDigit, Integer> DIGIT_NUMBERS = new HashMap<BarsDigit, Integer>();
	static {
		DIGIT_NUMBERS.put(new BarsDigit(" _ ", 
										"| |", 
										"|_|"), 0);
		
		DIGIT_NUMBERS.put(new BarsDigit("   ", 
										"  |", 
										"  |"), 1);
		
		DIGIT_NUMBERS.put(new BarsDigit(" _ ", 
										" _|", 
										"|_ "), 2);
		
		DIGIT_NUMBERS.put(new BarsDigit(" _ ", 
										" _|", 
										" _|"), 3);
		
		DIGIT_NUMBERS.put(new BarsDigit("   ", 
										"|_|", 
										"  |"), 4);
		
		DIGIT_NUMBERS.put(new BarsDigit(" _ ", 
										"|_ ", 
										" _|"), 5);
		
		DIGIT_NUMBERS.put(new BarsDigit(" _ ", 
										"|_ ", 
										"|_|"), 6);
		
		DIGIT_NUMBERS.put(new BarsDigit(" _ ", 
										"  |", 
										"  |"), 7);
		
		DIGIT_NUMBERS.put(new BarsDigit(" _ ", 
										"|_|", 
										"|_|"), 8);
		
		DIGIT_NUMBERS.put(new BarsDigit(" _ ", 
										"|_|", 
										"  |"), 9);
	}
	
	/**
	 * Returns the number represented by the input characters
	 * @param top - the 3 top characters of the digit
	 * @param middle - the 3 medium characters of the digit
	 * @param bottom - the 3 bottom characters of the digit
	 * @return - the number represented by the input characters
	 * @throws MalformedNumberException - thrown if the input characters do not represent a real number
	 */
	public static Integer getNumber ( String topLine, String middleLine, String bottomLine, int numberPos, int topLinePos ) throws MalformedNumberException {
		
		int startIndex = numberPos * CHARACTERS_PER_NUMBER_IN_LINE;
		int endIndex = startIndex + CHARACTERS_PER_NUMBER_IN_LINE;
		
		//Get the characters of the requested number
		String top = topLine.substring(startIndex, endIndex);
		String middle = middleLine.substring(startIndex, endIndex);
		String bottom = bottomLine.substring(startIndex, endIndex);
		
		BarsDigit digit = new BarsDigit(top, middle, bottom);
		Integer number = DIGIT_NUMBERS.get(digit);

		//If number is not found, throw an exception 
		if ( number == null ) {
			throw new MalformedNumberException("Number " + numberPos + " at line " + topLinePos + " is malformed.");
		}
		
		return number;
	}
}
