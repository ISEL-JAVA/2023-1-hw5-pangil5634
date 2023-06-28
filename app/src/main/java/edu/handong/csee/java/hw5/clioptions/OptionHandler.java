package edu.handong.csee.java.hw5.clioptions;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

/**
 * This class is about Option function
 */
public class OptionHandler {

	private String task;
	private String dataInputFilePath;
	private String dataOutputFilePaht;
	private String inputValues;
	private boolean helpRequested;

	/**
	 * This is getter for task.
	 * 
	 * @return value of task(engine name)
	 */
	public String getTask() {
		return task;
	}

	/**
	 * This is setter for task.
	 * 
	 * @param task value of task(engine name)
	 */
	public void setTask(String task) {
		this.task = task;
	}

	/**
	 * This is getter for data Input File Path.
	 * 
	 * @return value of data input file path
	 */
	public String getDataInputFilePath() {
		return dataInputFilePath;
	}

	/**
	 * This is setter for data Input File Path.
	 * 
	 * @param dataInputFilePath value of data input file path
	 */
	public void setDataInputFilePath(String dataInputFilePath) {
		this.dataInputFilePath = dataInputFilePath;
	}

	/**
	 * This is getter for data Ouput FIle Path.
	 * 
	 * @return value of data Ouput FIle Path.
	 */
	public String getDataOutputFilePath() {
		return dataOutputFilePaht;
	}

	/**
	 * This is setter for data Ouput FIle Path.
	 * 
	 * @param dataOutputFilePaht value of data Ouput FIle Path.
	 */
	public void setDataOutputFilePaht(String dataOutputFilePaht) {
		this.dataOutputFilePaht = dataOutputFilePaht;
	}

	/**
	 * This is getter for input values
	 * 
	 * @return value of data input values
	 */
	public String getInputValues() {
		return inputValues;
	}

	/**
	 * This is setter for input values
	 * 
	 * @param inputValues value of data input values
	 */
	public void setInputValues(String inputValues) {
		this.inputValues = inputValues;
	}

	/**
	 * This is getter for help requested.
	 * 
	 * @return value of data help requested.
	 */
	public boolean getHelpRequested() {
		return helpRequested;
	}

	/**
	 * This is setter for help requested.
	 * 
	 * @param helpRequested value of data help requested.
	 */
	public void setHelpRequested(boolean helpRequested) {
		this.helpRequested = helpRequested;
	}

	/**
	 * This method is add options methods.
	 * 
	 * @return created options
	 */
	public Options createOptions() {
		Options options = new Options();

		// // add options by using OptionBuilder
		// options.addOption(Option.builder("i").longOpt("ipath")
		// .desc("Set a path of a data input file. It must work with -t SQRT and -o
		// options together. e.g., -t SQRT -i file.csv -o output.csv")
		// .hasArg()
		// .argName("A data file path to read")
		// // .required()
		// .build());

		// // add options by using OptionBuilder
		// options.addOption(Option.builder("o").longOpt("opath")
		// .desc("Set a path of a data output file.")
		// .hasArg()
		// .argName("A data file path to write")
		// // .required()
		// .build());

		// // add options by using OptionBuilder
		// options.addOption(Option.builder("v").longOpt("values")
		// .desc("Set input values (separator: space), e.g. \"23 21 2\"")
		// .hasArg()
		// .argName("Input values for a task.")
		// // .required()
		// .build());

		// // add options by using OptionBuilder
		// options.addOption(Option.builder("t").longOpt("task")
		// .desc("Set a task. The -t or -i options must be set as well.")
		// .hasArg()
		// .argName("A task name")
		// .required()
		// .build());

		// // add options by using OptionBuilder
		// options.addOption(Option.builder("h").longOpt("help")
		// .desc("Show the help page")
		// .build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("i").longOpt("ipath")
				.desc("Set a path for a data input file. It must work with -t SQRT, -t MAX, or -t MIN and -o options together. e.g., "
						+ "-t SQRT -i file.csv -o "
						+ "output.csv")
				.hasArg()
				.argName("A data file/directory path to read")
				// .required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("o").longOpt("opath")
				.desc("Set a path for a data output file.")
				.hasArg()
				.argName("A data file path to write")
				// .required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("t").longOpt("task")
				.desc("Set a task. The -t or -i options must be set as well.")
				.hasArg() // this option is intended not to have an option value but just an option
				.argName("A task name")
				.required() // this is an optional option. So disabled required().
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("v").longOpt("values")
				.desc("Set input values (separator: space), e.g. \"23 21 2\"")
				.hasArg() // this option is intended not to have an option value but just an option
				.argName("Input values for a task.")
				// .required() // this is an optional option. So disabled required().
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
				.desc("Show the help page")
				.build());
		return options;
	}

	/**
	 * This method is save the data that arguments inputs.
	 * 
	 * @param options onw options values;
	 * @param args    arguments inputs
	 * @return result(false or true)
	 */
	public boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);
			
			task = cmd.getOptionValue("t");
			dataInputFilePath = cmd.getOptionValue("i");
			dataOutputFilePaht = cmd.getOptionValue("o");
			inputValues = cmd.getOptionValue("v");
			helpRequested = cmd.hasOption("h");

		} catch (Exception e) {

			printHelp(options);
			return false;
		}

		return true;
	}

	/**
	 * This method is provied the info about print Help
	 * 
	 * @param options onw options values;
	 */
	public void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		// String header = "Math Calculator";
		// String footer = "This is the 2023-1 HW4 help page.";
		String header = "Math Calculator";
		String footer = "\nThis is the 2023-1 HW5 help page.\n\n";
		formatter.printHelp("calculator", header, options, footer, true);
	}
}