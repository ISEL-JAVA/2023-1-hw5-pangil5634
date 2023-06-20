package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This interface provides commonly needed methods.
 */
public interface Computable {
    /**
     * This is the initial setup process.
     * 
     * @param args These parameters point to the engineName and number(s) as
     *             arguments entered when running the program.
     * @throws MinimumInputNumberException 
     */
    public void setInput(String[] args) throws MinimumInputNumberException, InvalidCommandException, NegativeNumberException, MyNumberFormatException, OneInputException;

    /**
     * This is the process of making calculations.
     */
    public void compute();

    /**
     * This is the process of getter the result.
     * 
     * @return is the stored result value
     */
    public double getResult();
}
