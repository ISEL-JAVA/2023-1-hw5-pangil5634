package edu.handong.csee.java.hw5.thread;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
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
	private ArrayList<ArrayList<String>> a = new ArrayList<ArrayList<String>>();
	private List<String> csvFiles = new ArrayList<>();

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
	 * This method is getter for arrayList
	 * 
	 * @return
	 */
	public ArrayList<ArrayList<String>> getA() {
		return a;
	}

	/**
	 * This method is setter for arrayList
	 * 
	 * @param a
	 */
	public void setA(ArrayList<ArrayList<String>> a) {
		this.a = a;
	}
	
	/**
	 * This method is getter for setCsvFiles
	 * 
	 * @return
	 */
	public List<String> getCsvFiles() {
		return csvFiles;
	}

	/**
	 * This method is setter for setCsvFiles
	 * 
	 * @param csvFiles
	 */
	public void setCsvFiles(List<String> csvFiles) {
		this.csvFiles = csvFiles;
	}

	
	
	//----------------------------------------------------------------------------------------------------------------
	
	/**
	 * This method is that read data for csv files.
	 * 
	 * @param filePath
	 * @return ArrayList<ArrayList<String>>
	 */
	public ArrayList<ArrayList<String>> readCSV(String filePath) {

		ArrayList<String> b = FileManager.readLinesFromAtxtFile(filePath);

//		System.out.println(b);
		a.add(calculate(b));


		return a;
	}

	/**
	 * This method is that write data on csv files.
	 * 
	 * @param filePath
	 * @param csvData
	 */
	public void writeCSV(String inputfilePath, String outputfilePath, ArrayList<ArrayList<String>> csvData,
			int taskId) {

		File file = new File(inputfilePath.substring(0, inputfilePath.length()-4) + "-" + outputfilePath);

		System.out.println(file + ", " + csvData.get(taskId));
		
		OptionHandler OH = new OptionHandler();
		try {

			FileWriter fileWriter = new FileWriter(file);
//			BufferedWriter bufWriter = new BufferedWriter(fileWriter);
            CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT);
			

			if(csvData.get(taskId).size()%FileManager.size == 0) {
				for (int i = 0; i < csvData.get(taskId).size(); i++) {
					
					if ((i +1) % (FileManager.size) == 0 && i != csvData.get(taskId).size() - 1) {
						csvPrinter.printRecord(csvData.get(taskId).get(i));
						fileWriter.write("\n");
					} else {
						csvPrinter.printRecord(csvData.get(taskId).get(i));
						if(i != csvData.get(taskId).size()-1)
							fileWriter.write(",");
	
					}
				}
			} else {
				for (int i = 0; i < csvData.get(taskId).size(); i++) {
					
					if ((i +1) % (FileManager.size+1) == 0 && i != csvData.get(taskId).size() - 1) {
						if(i <= FileManager.size+1) {
						csvPrinter.printRecord(csvData.get(taskId).get(i));
						fileWriter.write("\n");
						} else {
							csvPrinter.printRecord(csvData.get(taskId).get(i));
							fileWriter.write("\n");
						}
					
					} else {
						csvPrinter.printRecord(csvData.get(taskId).get(i));
						if(i != csvData.get(taskId).size()-1)
							fileWriter.write(",");
	
					}
				}
			}
			fileWriter.write("\n");

			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * This is constructor for CSVFileCalculator
	 * 
	 * @param inputPath
	 * @param outputPath
	 * @param taskName
	 */
	public CSVFileCalculator(String inputPath, String outputPath, String taskName) {
		this.taskName = taskName;
		this.inputPath = inputPath;
		this.outputPath = outputPath;
	}

	/**
	 * This method is that calculate for MAX, MIN, SQRT
	 * 
	 * @param taskName
	 */
	public ArrayList<String> calculate(ArrayList<String> a1) {
	    ArrayList<String> result = new ArrayList<String>();

	    for (String data : a1) {
	        try {
	            double value = Double.parseDouble(data);
	            double calculatedValue = 0.0;


	            switch (taskName) {
	                case "MAX":
	                    calculatedValue = Math.max(value, calculatedValue);
	                    break;
	                case "MIN":
	                    calculatedValue = Math.min(value, calculatedValue);
	                    break;
	                case "SQRT":
	                    calculatedValue = Math.sqrt(value);
	                    break;
	                default:

	                    throw new InvalidCommandException("Invalid task name: " + taskName);
	            }

	            result.add(Double.toString(calculatedValue));
	        } catch (NumberFormatException e) {

	            throw new MyNumberFormatException("Invalid number format: " + data);
	        } catch (Exception e) {

	            throw new RuntimeException(e.getMessage());
	        }
	    }

	    return result;
	}

	/**
	 * This method is that run This class program.
	 */
	@Override
	public void run() {

		File folder = new File(inputPath);
		File[] files = folder.listFiles();

		if (files != null) {
			for (File file : files) {
				if (file.isFile() && file.getName().endsWith(".csv")) {
					csvFiles.add(file.getPath());
				}
			}
		}


	}

}
