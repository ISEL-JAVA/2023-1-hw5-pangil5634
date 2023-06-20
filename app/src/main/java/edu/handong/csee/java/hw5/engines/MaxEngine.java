package edu.handong.csee.java.hw5.engines;

import java.util.Arrays;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This class is the MAXEngine for doing find the Maximum number.
 * 
 */
public class MaxEngine implements Computable {
    private static final String engineName = "MAX";
    private double result = 0;
    private double[] inputs = null;

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
    public void setResult(double result) {
        this.result = result;
    }

    /**
     * This is the getter of Inputs.
     * 
     * @return is the stored array value.
     */
    public double[] getInputs() {
        return inputs;
    }

    /**
     * This is the setter of Inputs.
     * 
     * @param inputs is the array value that the user wants to store
     */
    public void setInputs(double[] inputs) {
        this.inputs = inputs;
    }

    /**
     * This is the method of setting Inputs.
     * 
     * @param args These parameters point to the engineName and number(s) as
     *             arguments entered when running the program.
     */
    public void setInput(String[] args) throws MinimumInputNumberException {
        int count = 0;
        for (int i = 0; i < args.length; i++) {
            count++;
        }

        if (count < 2) {
            // InputChecker.printErrorMesssageForMinimumRequiredInputsAndExit(engineName,
            // 2);
            throw new MinimumInputNumberException(
                    "Exception-02: You need at least " + 2 + " input values for " + engineName.toUpperCase() + ".");
        }

        inputs = new double[args.length];

        for (int i = 0; i < args.length; i++) {
            inputs[i] = Double.parseDouble(args[i]);
        }

    }

    /**
     * This is the method of computing values.
     */
    public void compute() {
        Arrays.sort(inputs);
        result = inputs[inputs.length - 1];
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
