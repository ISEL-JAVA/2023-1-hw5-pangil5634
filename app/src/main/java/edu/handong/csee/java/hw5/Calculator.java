package edu.handong.csee.java.hw5;

import java.util.ArrayList;
import java.util.Arrays;


import org.apache.commons.cli.Options;

import edu.handong.csee.java.hw5.clioptions.*;
import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.*;
import edu.handong.csee.java.hw5.fileutil.*;

/**
 * This is main class for Calculator
 */
public class Calculator {
	/**
	 * This main methods for Calculator
	 * 
	 * @param args These parameters point to the engineName and number(s) as
	 *             arguments entered when running the program.
	 */
	public static void main(String[] args) throws MyNumberFormatException, MinimumInputNumberException,
			InvalidCommandException, NegativeNumberException, OneInputException {

		Calculator myCalculator = new Calculator();

		myCalculator.run(args);
	}

	/**
	 * This is the method that the program executes.
	 * 
	 * @param args These parameters point to the engineName and number(s) as
	 *             arguments entered when running the program.
	 * @throws OneInputException
	 * @throws NegativeNumberException
	 * @throws InvalidCommandException
	 * @throws MinimumInputNumberException
	 * @throws MyNumberFormatException
	 */
	public void run(String[] args) throws MyNumberFormatException, MinimumInputNumberException, InvalidCommandException,
			NegativeNumberException, OneInputException {
		OptionHandler OH = new OptionHandler();
		Options options = OH.createOptions();

		// FM.writeAtxtFile(OH.getDataOutputFilePaht(), a);
		try {
			
			if (OH.parseOptions(options, args)) {

				String engineName = OH.getTask().toUpperCase();
				String str = OH.getInputValues();
				Computable engine = null;

				switch (engineName) {
					case "LCM":
						engine = new LCMEngine();
						break;
					case "GCD":
						engine = new GCDEngine();
						break;
					case "SQRT":
						engine = new SQRTEngine();
						break;
					case "FACTORIAL":
						engine = new FactorialEngine();
						break;
					case "FIBONACCI":
						engine = new FibonacciEngine();
						break;
					case "MAX":
						engine = new MaxEngine();
						break;
					case "MIN":
						engine = new MinEngine();
						break;
					case "CUBEVOL":
						engine = new CubeVolEngine();
						break;
					case "SPHEREVOL":
						engine = new SphereVolEngine();
						break;
					default:

						throw new InvalidCommandException("Exception-01: Invalid command: " + engineName
								+ "\nPlease put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");
				}
				
				if(str == null && OH.getDataInputFilePath() == null){
					OH.printHelp(options);
					System.exit(0);
				}
				if (str != null && OH.getDataInputFilePath() != null) {
					OH.printHelp(options);
					System.exit(0);
				}
				if (str != null) {
					if (OH.getInputValues() == null) {
						OH.printHelp(options);
						System.exit(0);
					}
					String[] values = str.split("\\s+");
					if (values.length < 1) {
						throw new InvalidCommandException(
								"Exception-01: Invalid command: \nPlease put a computing engine option such as LCM, GCD, SQRT, FACTORIAL, FIBONACCI, MAX, MIN, CUBEVOL, and SPHEREVOL. For example, ./app  MAX 12 4 5 45 100");

					}

					for (int i = 0; i < values.length; i++) {
						if (!values[i].matches("[+-]?\\d*(\\.\\d+)?")) {
							throw new MyNumberFormatException(
									"Exception-05: The input value should be converted into a number. (" + values[i]
											+ " is not a number value for " + engineName + ".)");
						}

					}
					engine.setInput(values);
					engine.compute();
					System.out.println("The result of " + engineName + " is " + engine.getResult() + ".");

				} else if (str == null) {
					ArrayList<String> a = FileManager.readLinesFromAtxtFile(OH.getDataInputFilePath());
					if(OH.getTask().toUpperCase().equals("SQRT")){
						if (a != null) {
							String[] temp = new String[1];

							for (int i = FileManager.size; i < a.size(); i++) {
								temp[0] = a.get(i);
								engine.setInput(temp);
								engine.compute();

								a.set(i, Double.toString(engine.getResult()));
							}
							if (OH.getDataOutputFilePath() != null){
								FileManager.writeAtxtFile(OH.getDataOutputFilePath(), a);
							}
							else {
								OH.printHelp(options);
								System.exit(0);
							}
						} else {
							OH.printHelp(options);
							System.exit(0);
						}
					} else {
						
						OH.printHelp(options);
						System.exit(0);
				}
			} 
		}

		} catch (OneInputException | NegativeNumberException | InvalidCommandException | MinimumInputNumberException
				| MyNumberFormatException e) {
			String message = e.getMessage();
			System.out.println(message);
		}

	}

}