package edu.handong.csee.java.hw5.thread;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.cli.Options;
import org.apache.commons.csv.*;
import edu.handong.csee.java.hw5.engines.*;
import edu.handong.csee.java.hw5.exceptions.InvalidCommandException;
import edu.handong.csee.java.hw5.exceptions.MinimumInputNumberException;
import edu.handong.csee.java.hw5.exceptions.MyNumberFormatException;
import edu.handong.csee.java.hw5.exceptions.NegativeNumberException;
import edu.handong.csee.java.hw5.exceptions.OneInputException;
import edu.handong.csee.java.hw5.fileutil.*;
import edu.handong.csee.java.hw5.MyLinkedList;
import edu.handong.csee.java.hw5.clioptions.*;


/**
 * This class is the class to use when using at least two CSV files.
 * 
 * @author kimkwang-il
 *
 */

public class CSVFileCalculator implements Runnable {
	


	private String taskName;
	private String inputPath;
	private String outputPath;
	private MyLinkedList<MyLinkedList<String>> a = new MyLinkedList<MyLinkedList<String>>();

	/**
	 * The method is getter for TaskName
	 * 
	 * @return taskName
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * The method is setter for TaskName
	 * 
	 * @param taskName
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/**
	 * The method is getter for inputPath
	 * 
	 * @return inputPath
	 */
	public String getInputPath() {
		return inputPath;
	}

	/**
	 * The method is setter for inputPath
	 * 
	 * @param inputPath
	 */
	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	/**
	 * The method is getter for outputPath
	 * 
	 * @return outputPath
	 */
	public String getOutputPath() {
		return outputPath;
	}

	/**
	 * The method is setter for outputPath
	 * 
	 * @param outputPath
	 */
	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	/**
	 * This method is getter for MyLinkedList
	 * 
	 * @return
	 */
	public MyLinkedList<MyLinkedList<String>> getA() {
		return a;
	}

	/**
	 * This method is setter for MyLinkedList
	 * 
	 * @param a
	 */
	public void setA(MyLinkedList<MyLinkedList<String>> a) {
		this.a = a;
	}

	// ----------------------------------------------------------------------------------------------------------------

	/**
	 * This method is that read data for csv files.
	 * 
	 * @param filePath
	 * @return MyLinkedList<MyLinkedList<String>>
	 */
	public MyLinkedList<MyLinkedList<String>> readCSV(String filePath) {

		try {
			Reader reader = Files.newBufferedReader(Paths.get(filePath));
			CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
			int count = 0;

			for (CSVRecord csvRecord : csvParser) {
//				MyLinkedList<String> sublist = new MyLinkedList<>(Arrays.asList(csvRecord.values()));
				MyLinkedList<String> sublist = new MyLinkedList<String>();
//			    System.out.println(csvRecord);
				
				for(int i = 0; i < csvRecord.values().length; i++) {
					sublist.addANodeToTail(csvRecord.get(i));

				}
				
//			    for (String value : csvRecord.values()) {
//			        sublist.addANodeToTail(value);
//			    }

//				System.out.println(sublist instanceof ArrayList);
			    a.addANodeToTail(sublist);

//				a.add(sublist);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
//		System.out.println(a);
//	    System.out.println(a.getValueAt(1));
		return a;
	}

	/**
	 * This method is that write data on csv files.
	 * 
	 * @param filePath
	 * @param csvData
	 * @throws IOException
	 * @throws OneInputException
	 * @throws NegativeNumberException
	 * @throws InvalidCommandException
	 * @throws MinimumInputNumberException
	 * @throws MyNumberFormatException
	 * @throws IndexOutOfBoundsException
	 */
	public void writeCSV(String filePath, MyLinkedList<MyLinkedList<String>> csvData)
			throws IOException, MyNumberFormatException, MinimumInputNumberException, InvalidCommandException,
			NegativeNumberException, OneInputException {
		try {
			if (csvData.getValueAt(0) == null) {
				System.out.println();
			}
		} catch (IndexOutOfBoundsException e) {
			Thread.currentThread().interrupt();
			return;
		}
		String fileName = filePath.substring(filePath.lastIndexOf("/") + 1, filePath.indexOf(".csv"));

		int startIndex = outputPath.lastIndexOf("/");
		StringBuffer sb = new StringBuffer();
		sb.append(outputPath);
		if (startIndex > -1) {
			outputPath = sb.insert(startIndex + 1, fileName + "-").toString();
		} else {
			outputPath = sb.insert(0, fileName + "-").toString();
		}
		



//		System.out.println("before : " + csvData);
//		System.out.println("after : " + csvData);

		if (taskName.equals("SQRT")) {
			csvData = calculate(csvData);
			File file = new File(outputPath);


			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufWriter = new BufferedWriter(fileWriter);
			CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
			
			String temp1 = "";
			
			for(int j = 0; j < csvData.getValueAt(0).length(); j++) {
				temp1 = temp1 + csvData.getValueAt(0).getValueAt(j);
				if(j == csvData.getValueAt(0).length()-1) {
					temp1 = temp1 + "\n";
				} else {
					temp1 = temp1 + ", ";
				}
			}
//			System.out.println(temp1);
//			fileWriter.write(String.join(",", csvData.getValueAt(0)) + "\n");
			fileWriter.write(temp1);
			temp1 = "";
			
//			for ( int i = 1; i < csvData.length(); i++) {
//				for(int j = 0; j < csvData.getValueAt(i).length(); j++) {
//					temp1 = temp1 + csvData.getValueAt(i).getValueAt(j);
//					if(j == csvData.getValueAt(0).length()-1) {
//						temp1 = temp1 + "\n";
//					} else {
//						temp1 = temp1 + ", ";
//					}
//				}
//				csvPrinter.printRecord(temp1);
//			}

			for ( int i = 1; i < csvData.length(); i++) {
				for(int j = 0; j < csvData.getValueAt(i).length(); j++) {
					csvPrinter.print(csvData.getValueAt(i).getValueAt(j));
					if(j == csvData.getValueAt(i).length()-1) {
//						fileWriter.write("\n");
						csvPrinter.printRecord();
					} 
				}

			}
			fileWriter.close();
		} else {
			try {
				if (taskName.equals("MIN")) {
					csvData.getValueAt(0).addANodeToTail("MIN");
				} else if (taskName.equals("MAX")) {

					csvData.getValueAt(0).addANodeToTail("MAX");
				}
			} catch (IndexOutOfBoundsException e) {
				Thread.currentThread().interrupt();
				return;
			}
			csvData = calculate(csvData);
			File file = new File(outputPath);

			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufWriter = new BufferedWriter(fileWriter);
			CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
			
			String temp2 = "";
			for(int j = 0; j < csvData.getValueAt(0).length(); j++) {
				temp2 = temp2 + csvData.getValueAt(0).getValueAt(j);
				if(j == csvData.getValueAt(0).length()-1) {
					temp2 = temp2 + "\n";
				} else {
					temp2 = temp2 + ", ";
				}

			}
			fileWriter.write(temp2);
//			fileWriter.write(String.join(",", csvData.getValueAt(0)) + "\n");
//			for (int i = 1; i < csvData.length(); i++) {
//
//				csvPrinter.printRecord(csvData.getValueAt(i));
//			}
			for ( int i = 1; i < csvData.length(); i++) {
				for(int j = 0; j < csvData.getValueAt(i).length(); j++) {
					csvPrinter.print(csvData.getValueAt(i).getValueAt(j));
					if(j == csvData.getValueAt(i).length()-1) {
//						fileWriter.write("\n");
						csvPrinter.printRecord();
					} 
				}

			}
			fileWriter.close();
		}

	}

	/**
	 * This is constructor for CSVFileCalculator
	 * 
	 * @param inputPath
	 * @param outputPath
	 * @param taskName
	 */
	public CSVFileCalculator(String taskName, String inputPath, String outputPath) {
		this.taskName = taskName;
		this.inputPath = inputPath;
		this.outputPath = outputPath;
	}

	/**
	 * This method is that calculate for MAX, MIN, SQRT
	 * 
	 * @param taskName
	 * @throws OneInputException
	 * @throws NegativeNumberException
	 * @throws InvalidCommandException
	 * @throws MinimumInputNumberException
	 * @throws MyNumberFormatException
	 */
	public MyLinkedList<MyLinkedList<String>> calculate(MyLinkedList<MyLinkedList<String>> a1) throws MyNumberFormatException,
			MinimumInputNumberException, InvalidCommandException, NegativeNumberException, OneInputException {
		Computable engine = null;

		if (taskName.toUpperCase().equals("SQRT")) {   
			try {
				if (a1.getValueAt(1) == null) {
					System.out.println(); 
				}
			} catch (IndexOutOfBoundsException e) {
				throw new OneInputException("Exception-04: You need one input value for " + taskName.toUpperCase() + ".");

			}
			engine = new SQRTEngine();
//			for(ArrayList<String> data : a1) {
		
			for (int i = 1; i < a1.length(); i++) {

				MyLinkedList<String> result = new MyLinkedList<String>();

//				for (String d1 : a1.getValueAt(i)) {
				for(int j = 0; j < a1.getValueAt(i).length(); j++) {
					String d1 = a1.getValueAt(i).getValueAt(j);

					Double calculatedValue = 0.0;
					String[] temp = new String[1];
					
					
					temp[0] = d1;
					if (!temp[0].matches("[+-]?\\d*(\\.\\d+)?")) {
						throw new MyNumberFormatException(
								"Exception-05: The input value should be converted into a number. (" + temp[0]
										+ " is not a number value for " + taskName + ".)");
					}
					
					engine.setInput(temp);
					engine.compute();
					calculatedValue = engine.getResult();
////					calculatedValue = Math.sqrt((double)value);
					result.addANodeToTail(calculatedValue.toString());
				}
//				System.out.println(result);
//				for(int k = 0; k < result.length(); k++) {
//					System.out.println(result.getValueAt(k));
//				}
				a1.setValueAt(i, result);

			
			}

		} else if (taskName.toUpperCase().equals("MAX") || taskName.toUpperCase().equals("MIN")) {
			try {
				if (a1.getValueAt(1) == null) {
					System.out.println();
				}
			} catch (IndexOutOfBoundsException e) {
				throw new MinimumInputNumberException(
						"Exception-02: You need at least " + 2 + " input values for " + taskName.toUpperCase() + ".");

			}
			if (taskName.toUpperCase().equals("MAX"))
				engine = new MaxEngine();
			else if (taskName.toUpperCase().equals("MIN"))
				engine = new MinEngine();

			for (int i = 1; i < a1.length(); i++) {

				int arrayListSize = a1.getValueAt(i).length();

				String[] temp = new String[arrayListSize];

				int count = 0;
//				for (String d1 : a1.getValueAt(i)) {
				for(int j = 0; j < a1.getValueAt(i).length(); j++) {
					String d1 = a1.getValueAt(i).getValueAt(j);
					temp[count] = d1;
					count++;
				}
				for (int j = 0; j < a1.getValueAt(i).length(); j++) {
					if (!temp[j].matches("[+-]?\\d*(\\.\\d+)?")) {
						throw new MyNumberFormatException(
								"Exception-05: The input value should be converted into a number. (" + temp[j]
										+ " is not a number value for " + taskName + ".)");
					}

				}

				engine.setInput(temp);
				engine.compute();
				int engineResult = (int) engine.getResult();

				a1.getValueAt(i).addANodeToTail(engineResult + "");

			}
		}
//		ArrayList<String> result = new ArrayList<String>();
//
//		for (String data : a1) {
//			try {
//				double value = Double.parseDouble(data);
//				double calculatedValue = 0.0;
//
//				switch (taskName) {
//				case "MAX":
//					calculatedValue = Math.max(value, calculatedValue);
//					break;
//				case "MIN":
//					calculatedValue = Math.min(value, calculatedValue);
//					break;
//				case "SQRT":
//					calculatedValue = Math.sqrt(value);
//					break;
//				default:
//
//					throw new InvalidCommandException("Invalid task name: " + taskName);
//				}
//
//				result.add(Double.toString(calculatedValue));
//			} catch (NumberFormatException e) {
//
//				throw new MyNumberFormatException("Invalid number format: " + data);
//			} catch (Exception e) {
//
//				throw new RuntimeException(e.getMessage());
//			}
//		}

		return a1;
	}

	/**
	 * This method is that run This class program.
	 */
	@Override
	public void run() {

		try {
			writeCSV(inputPath, readCSV(inputPath));
		} catch (OneInputException | NegativeNumberException | InvalidCommandException | MinimumInputNumberException
				| MyNumberFormatException | IOException e) {

			String message = e.getMessage();
			System.out.println(message);
		}
	}

}
