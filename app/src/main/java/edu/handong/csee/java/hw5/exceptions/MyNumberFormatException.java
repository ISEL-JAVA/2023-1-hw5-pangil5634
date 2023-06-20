package edu.handong.csee.java.hw5.exceptions;

import java.lang.NumberFormatException;

/**
 * This class handles the exception that is thrown when a value other than an
 * integer is entered.
 */
public class MyNumberFormatException extends NumberFormatException {
	/**
	 * This is the constructor for that class.
	 */
	public MyNumberFormatException() {
	}

	/**
	 * This class is a constructor with parameters of that class.
	 * 
	 * @param message Takes the message passed as an argument when the exception is
	 *                thrown as the message variable.
	 */
	public MyNumberFormatException(String message) {
		super(message);
	}
}
