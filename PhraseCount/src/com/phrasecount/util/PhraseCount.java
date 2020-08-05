package com.phrasecount.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/*
 * A utility class which searches for a phrase in a text and tells how
 * many times does the phrase occur in the text.
 */
public class PhraseCount {
	
	/*
	 * Method which uses Pattern & Matcher from Java util lib.
	 * It compiles the searched phrase into a regular expression pattern.
	 * Then the pattern is applied to the whole text and for every match a
	 * counter is incremented.
	 * The method has the necessary exception handling.
	 * @param String phrase - the searched phrase
	 * @param String fileContent - the whole text of the file to be searched for the phrase
	 * @return int matches - the number of times the phrase was found in the text
	 */
	public static int phraseCount(String phrase, String fileContent){
		int matches = 0;
		try{
			//The Pattern can be tweaked with additional parameters, but for the purpose of this task
			//using no additional parameters is sufficient. Example:
			//Pattern pattern = Pattern.compile(phrase, Pattern.DOTALL|Pattern.COMMENTS|Pattern.MULTILINE);
			Pattern pattern = Pattern.compile(phrase);
			Matcher matcher = pattern.matcher(fileContent);
			while(matcher.find()){
				matches++;
			}
		} catch (PatternSyntaxException e) {
			System.err.println("!!!PHRASE EXCEPTION - Unable to compile RegularExpression!!!");
			e.printStackTrace();
		}
		return matches;
	}
}
