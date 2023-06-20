package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This class is the LCMEngine for doing calculate the LCM two numbers.
 * 
 */
public class LCMEngine implements Computable {
    private static final String engineName = "LCM";
    private int result = 0;
    private int a[];

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
     * @param result is the result value that the user wants to store
     */
    public void setResult(int result) {
        this.result = result;
    }

    /**
     * This is getter of number list
     * 
     * @return is the stored array value.
     */
    public int[] getA() {
        return a;
    }

    /**
     * This is setter of number list
     * 
     * @param a is the array value that the user wants to store
     */
    public void setA(int[] a) {
        this.a = a;
    }

    /**
     * This is the method of setting Inputs.
     * 
     * @param args These parameters point to the engineName and number(s) as
     *             arguments entered when running the program.
     * @throws MinimumInputNumberException
     * @throws NegativeNumberException
     */
    public void setInput(String[] args) throws MinimumInputNumberException, NegativeNumberException {

        int count = 0;
        int negativeCount = 0;
        for (int i = 0; i < args.length; i++) {
            count++;
            if (Integer.parseInt(args[i]) < 0) {
                negativeCount++;
            }
        }

        if (count < 2) {
            // InputChecker.printErrorMesssageForMinimumRequiredInputsAndExit(engineName,
            // 2);
            throw new MinimumInputNumberException(
                    "Exception-02: You need at least " + 2 + " input values for " + engineName.toUpperCase() + ".");
        }
        if (negativeCount > 0) {
            // InputChecker.printErrorMesssageForNegativeInputsAndExit(engineName);
            throw new NegativeNumberException(
                    "Exception-03: The input value cannot be negative for " + engineName.toUpperCase() + ".");
        }

        a = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            a[i] = Integer.parseInt(args[i]);
        }
    }

    /**
     * This is the method of computing values.
     */
    public void compute() {

        int max = 0;
        for (int number : a) {
            if (number > max) {
                max = number;
            }
        }

        int lcm = max;
        boolean found = false;
        while (!found) {
            found = true;
            for (int number : a) {
                if (number != 0 && (lcm % number) != 0) {
                    found = false;
                    break;
                }
            }
            if (!found) {
                lcm += max;
            }
        }
        result = lcm;
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