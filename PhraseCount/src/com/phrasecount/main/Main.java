package com.phrasecount.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.phrasecount.util.FileToStringBuilder;
import com.phrasecount.util.PhraseCount;

/*
 * Task 1:
 * Write a Java program to read a .txt file and count how many times a given phrase 
 * appears in the file.
 */

public class Main {
	
	private static String fileInputPath;
	private static String fileFullPath;
	private static String defaultFile;
	private static String fileContent;
	private static String phrase;
	private static Scanner in;
	private static File resourceFile;
	
	public static void main(String[] args) throws FileNotFoundException {
		//Initialising variables.
		fileInputPath = "";
		fileFullPath = "";
		defaultFile = "./src/com/phrasecount/resources/Romeo&Juliet.txt";
		fileContent = "";
		phrase = "";
		in = new Scanner(System.in);
		resourceFile = null;

		loadFile(); //load file to be searched
		loadPhrase(); //load phrase to be searched for
			
		in.close(); //Closing scanner input stream.
	}
	
	/*
	 * Method for console user interface.
	 * It takes input from the user and loads the specified file into memory.
	 * @param - n/a
	 * @return - void
	 */
	private static void loadFile(){
		System.out.println("Please input the full file path including specified file " +
				"or type 'default*File' to use default file.");
		System.out.println("Example file path: ");
		System.out.println("C:/Users/user/Documents/Workspace/PhraseCount/src/com/" +
				"phrasecount/resources/Romeo&Juliet.txt - NOTE: the slashes depend on your OS.");
		System.out.println("Input '!quit' to exit program.");

		while (in.hasNext()) {
			fileInputPath = in.nextLine();
			if (fileInputPath.equals("default*File")) {
				fileFullPath = defaultFile;
			} else if (fileInputPath.equals("!quit")) {
				System.out.println("Exiting...");
				System.exit(0);
			} else {
				fileFullPath = fileInputPath;
			}
			resourceFile = new File(fileFullPath);
			//Making sure the specified file exists.
			//If it doesn't the user is prompted again.
			if (resourceFile.exists()) {
				break;
			} else {
				System.out.println("The file you specified was not found, " +
						"please try again or type '!quit' to exit: ");
			}
		}
		//Using the utility class to get the contents of the file.
		fileContent = FileToStringBuilder.readFiletoString(resourceFile);
	}
	
	/*
	 * Method for console user interface.
	 * It takes input form the user for the phrase which is to be searched for.
	 * @param - n/a
	 * @return - void
	 */
	private static void loadPhrase(){
		System.out.println("\nPlease input the phrase you would like to look for");
		phrase = in.nextLine();
		System.out.println("The phrase you entered was found "
				+ PhraseCount.phraseCount(phrase, fileContent)
				+ " times in the text file.");
	}
}
