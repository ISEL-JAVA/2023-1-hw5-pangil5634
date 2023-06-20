package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This class is the FibonacciEngine for doing compute Fibonacci number.
 * 
 */
public class FibonacciEngine implements Computable {
    private static final String engineName = "FIBONACCI";
    private double result = 1;
    private int n = 0;

    /**
     * This is the getter of engineName.
     * 
     * @return is the stored engineName value.
     */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * This is the setter of result.
     * 
     * @param result is the result value that the user wants to store.
     */
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * This is the getter of N.
     * 
     * @return is the stored Number value.
     */
    public int getN() {
        return n;
    }

    /**
     * This is the setter of N.
     * 
     * @param n is the Number value that the user wants to store.
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * This is the method of setting Inputs.
     * 
     * @param args These parameters point to the engineName and number(s) as
     *             arguments entered when running the program.
     * @throws OneInputException
     * @throws NegativeNumberException
     */
    public void setInput(String[] args) throws OneInputException, NegativeNumberException {
        int count = 0;
        for (int i = 0; i < args.length; i++) {
            count++;
        }

        if (count != 1) {
            // InputChecker.printErrorMesssageForTheNumberOfRequiredInputsAndExit(engineName,
            // 1);
            throw new OneInputException("Exception-04: You need one input value for " + engineName.toUpperCase() + ".");
        }

        n = Integer.parseInt(args[0]);
        if (n < 1) {
            // InputChecker.printErrorMesssageForNegativeInputsAndExit(engineName);
            throw new NegativeNumberException(
                    "Exception-03: The input value cannot be negative for " + engineName.toUpperCase() + ".");
        }
    }

    /**
     * This is the method of computing values.
     * 
     */
    public void compute() {

        if (n <= 1) {
            n = 1;
        } else {

            int fib = 1;
            int prevFib = 1;

            for (int i = 2; i < n; i++) {
                int temp = fib;
                fib += prevFib;
                prevFib = temp;
            }

            result = fib;
        }
    }

    /**
     * This is the getter of result.
     * 
     * @return is the stored result value
     */
    public double getResult() {
        return result;
    }

}
