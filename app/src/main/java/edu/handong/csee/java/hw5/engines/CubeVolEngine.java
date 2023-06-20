package edu.handong.csee.java.hw5.engines;

import edu.handong.csee.java.hw5.exceptions.*;

/**
 * This class is the CubeVolEngine for doing compute the volume of a cube.
 */

public class CubeVolEngine implements Computable {
    private static final String engineName = "CUBEVOL";

    private double sideLength = 0.0;
    private double volume = 0.0;

    /**
     * This is the getter of engineName.
     * 
     * @return is the stored engineName value.
     */
    public static String getEnginename() {
        return engineName;
    }

    /**
     * This is the getter of sideLength.
     * 
     * @return is the stored sideLength value.
     */
    public double getSideLength() {
        return sideLength;
    }

    /**
     * This is the setter of sideLength.
     * 
     * @param sideLength is the sideLength value that the user wants to store.
     */
    public void setSideLength(double sideLength) {
        this.sideLength = sideLength;
    }

    /**
     * This is the getter of volume.
     * 
     * @return is the stored volume value
     */

    public double getVolume() {
        return volume;
    }

    /**
     * This is the setter of volume.
     * 
     * @param volume is the volume value that the user wants to store.
     */
    public void setVolume(double volume) {
        this.volume = volume;
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

        sideLength = Double.parseDouble(args[0]);
        if (sideLength < 1) {
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
        volume = sideLength * sideLength * sideLength;
    }

    /**
     * This is the getter of result.
     * 
     * @return is the stored result value
     */

    public double getResult() {

        return volume;
    }
}
