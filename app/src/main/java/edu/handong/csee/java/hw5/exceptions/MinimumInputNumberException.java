package edu.handong.csee.java.hw5.exceptions;

/**
 * This class is an exception handling that is thrown when the minimum required
 * input value from the user is not met.
 */
public class MinimumInputNumberException extends Exception {
	/**
	 * This is the constructor for that class.
	 */
	public MinimumInputNumberException() {
	}

	/**
	 * This class is a constructor with parameters of that class.
	 * 
	 * @param message Takes the message passed as an argument when the exception is
	 *                thrown as the message variable.
	 */
	public MinimumInputNumberException(String message) {
		super(message);
	}
}
