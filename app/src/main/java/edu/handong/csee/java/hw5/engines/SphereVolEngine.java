package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This class is the SphereVolEngine for doing compute the volume of a Spehere.
 */
public class SphereVolEngine implements Computable {
    private static final String engineName = "SPHEREVOL";
    private double result = 0.0;
    private double radius = 0.0;

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
     * This is the getter of Radius.
     * 
     * @return s the stored radius value.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * This is the setter of Radius.
     * 
     * @param radius is the radius value that the user wants to store
     */
    public void setRadius(double radius) {
        this.radius = radius;
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

        radius = Double.parseDouble(args[0]);
        if (radius < 1) {
            // InputChecker.printErrorMesssageForNegativeInputsAndExit(engineName);
            throw new NegativeNumberException(
                    "Exception-03: The input value cannot be negative for " + engineName.toUpperCase() + ".");
        }

    }

    /**
     * This is the method of computing values.
     */
    public void compute() {
        result = (4.0 / 3.0) * Math.PI * (radius * radius * radius);
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
