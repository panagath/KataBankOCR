package com.panos.katabank.ocr.exceptions;

/**
 * This exception is thrown when the input file is malformed
 * 
 * @author panos
 *
 */
public class MalformedAccountsFileException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4486935171904745701L;

	public MalformedAccountsFileException(String message) {
        super(message);
    } 
}
