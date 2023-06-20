package edu.handong.csee.java.hw5.exceptions;

/**
 * This class handles the exception that is thrown when a negative number is
 * entered.
 */
public class NegativeNumberException extends Exception {
	/**
	 * This is the constructor for that class.
	 */
	public NegativeNumberException() {
	}

	/**
	 * This class is a constructor with parameters of that class.
	 * 
	 * @param message Takes the message passed as an argument when the exception is
	 *                thrown as the message variable.
	 */
	public NegativeNumberException(String message) {
		super(message);

	}
}
