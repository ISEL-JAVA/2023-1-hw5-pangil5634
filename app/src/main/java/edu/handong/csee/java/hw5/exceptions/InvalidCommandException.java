package edu.handong.csee.java.hw5.exceptions;

/**
 * This class is the exception handling for when nothing is input from the user.
 */
public class InvalidCommandException extends Exception {
	/**
	 * This is the constructor for that class.
	 */
	public InvalidCommandException() {
	}

	/**
	 * This class is a constructor with parameters of that class.
	 * 
	 * @param message Takes the message passed as an argument when the exception is
	 *                thrown as the message variable.
	 */
	public InvalidCommandException(String message) {
		super(message);
	}
}
