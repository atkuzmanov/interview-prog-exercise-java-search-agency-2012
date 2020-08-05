package com.phrasecount.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;

/*
 * A utility class for reading the contents of a file into a String.
 */
public class FileToStringBuilder {
	
	/*
	 * Method which reads the contents of a file and stores them into a String.
	 * The method has the necessary exception handling.
	 * @param File file - the file to be read
	 * @return String fileToStringResult - the string which contains all the contents
	 * of the text file
	 */
	public static String readFiletoString(File file) {
		BufferedReader resourceFile = null;
		String fileToStringResult = "";
		try {
			resourceFile = new BufferedReader(new FileReader(file));
			fileToStringResult = fileStringBuild(resourceFile);
			resourceFile.close();		
		} catch (BufferOverflowException e) {
			System.err.println("!!!BufferOverflowException!!!");
			e.printStackTrace();
		} catch (BufferUnderflowException e) {
			System.err.println("!!!BufferUnderflowException!!!");
			e.printStackTrace();
		} catch (Exception e) {
			System.err.println("!!!EXCEPTION!!!");
			e.printStackTrace();
		}
		return fileToStringResult;
	}
	
	/*
	 * A utility method which builds a String, using a StringBuilder,
	 * out of the contents of a BufferedReader.
	 * @param BufferedReader br - the BufferedReader with contents
	 * @return String fileToString - the String holding the contents of the BufferedReader
	 */
	private static String fileStringBuild(BufferedReader br){
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");
		String fileToString = "";
		try {
			while((fileToString = br.readLine())!= null) {
				stringBuilder.append(fileToString);
				stringBuilder.append(ls);
			}
		} catch (IOException e) {
			System.err.println("!!!IOException!!!");
			e.printStackTrace();
		}	
		return stringBuilder.toString();
	}
}
