package edu.handong.csee.java.hw5.fileutil;

import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.cli.Options;

import edu.handong.csee.java.hw5.clioptions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Path;
import edu.handong.csee.java.hw5.MyLinkedList;

/**
 * This class has the ability to get or save data from a file.
 */
public class FileManager {


	/**
	  * data one line data length
	  */
	public static int size;

	/**
	 * This method is for getting data from a file.
	 * 
	 * @param s This parameter points to the path to the file.
	 * @return Returns data values from a file stored as an ArrayList.
	 */
	public static MyLinkedList<String> readLinesFromAtxtFile(String s) {
		File file = new File(s);
		// Path p = Paths.get(s);
		// String path = p.toAbsolutePath().toString();
		// System.out.println(path);
		MyLinkedList<String> a = new MyLinkedList<String>();
		OptionHandler OH = new OptionHandler();
		Options options = OH.createOptions();

		try {
			// fis = new FileInputStream(file);
			FileReader filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);

			String line = "";
			while ((line = bufReader.readLine()) != null) {
				String[] tmp = line.split(",");
				for (int i = 0; i < tmp.length; i++) {
//					System.out.println(tmp[i]);
					a.addANodeToTail(tmp[i]);
					size = tmp.length;
				}

				}

			

		} catch (FileNotFoundException e) {
			
			OH.printHelp(options);
			System.exit(0);
			// e.printStackTrace();
			// System.exit(0);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return a;
	}

	/**
	 * This method saves the data you've worked with to a file.
	 * 
	 * @param s The path to the file to save
	 * @param a The ArrayList where you saved the data
	 */
	public static void writeAtxtFile(String s, MyLinkedList<String> a) {
		File file = new File(s);
		OptionHandler OH = new OptionHandler();
		try {
			// fis = new FileInputStream(file);
			FileWriter fileWriter = new FileWriter(file);
			BufferedWriter bufWriter = new BufferedWriter(fileWriter);
			if(a.length()%size == 0) {
				for (int i = 0; i < a.length(); i++) {
	
					if ((i +1) % (size) == 0 && i != a.length() - 1) {
						fileWriter.write(a.getValueAt(i));
						fileWriter.write("\n");
					} else {
						fileWriter.write(a.getValueAt(i));
						if(i != a.length()-1)
							fileWriter.write(",");
	
					}
				}
			} else {
				for (int i = 0; i < a.length(); i++) {
					
					if ((i +1) % (size+1) == 0 && i != a.length() - 1) {
						fileWriter.write(a.getValueAt(i));
						fileWriter.write("\n");
					} else {
						fileWriter.write(a.getValueAt(i));
						if(i != a.length()-1)
							fileWriter.write(",");
	
					}
				}
			}
			fileWriter.write("\n");

			
			fileWriter.flush();
			fileWriter.close();
			System.out.println("The "+ s +" file has been successfully written.");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}