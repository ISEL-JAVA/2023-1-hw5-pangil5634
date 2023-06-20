package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This class is the GCDEngine for doing calculate the GCD two numbers.
 * 
 */
public class GCDEngine implements Computable {
    private static final String engineName = "GCD";
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

        int min = 100;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] < min) {
                min = a[i];
            }
        }
        int gcd = 1;
        for (int i = 2; i <= min; i++) {
            boolean isDivisor = true;
            for (int j = 0; j < a.length; j++) {
                if (a[j] % i != 0) {
                    isDivisor = false;
                    break;
                }
            }
            if (isDivisor) {
                gcd = i;
            }
        }
        result = gcd;
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
