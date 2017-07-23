package com.logtoxml.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

import com.logtoxml.beans.DirectoryBean;
import com.logtoxml.util.LogToXMLConstants;

public class WritingSpecialCharsToFile {

	DirectoryBean objVarWriting;

	int count = 0;
	int status = 0;

	String currentLineSpecialChar = null;

	Pattern regex = Pattern.compile("[^A-Za-z0-9. >/<=\",?+@#&()_:;-]"); 

	public void writingSpecialCharsToFileAfterSearchingForDataOrSpecialChars(String directoryPath,
			File fileWithXMLDataOnly) {
		File fileFinal = new File(directoryPath, LogToXMLConstants.FILE_FINAL_RESULT);
		try (BufferedReader brWriteSpecialChar = new BufferedReader(new FileReader(fileWithXMLDataOnly));
				FileWriter fwWritingSpecialChars = new FileWriter(fileFinal);
				BufferedWriter bwWritingSpecialChars = new BufferedWriter(fwWritingSpecialChars);) {

			while ((currentLineSpecialChar = brWriteSpecialChar.readLine()) != null) {

				// TO-Do Change the logic to get special chars
				int j = 0;
				for (int i = 0; i < currentLineSpecialChar.length(); i++) {
					if (currentLineSpecialChar.substring(i, i + 1).equalsIgnoreCase(">")) {
						if (currentLineSpecialChar.substring(i + 1, (i + 1) + 1).equalsIgnoreCase("<")) {
						} else {
							while (!(currentLineSpecialChar.substring(j + 1, (j + 1) + 1).equalsIgnoreCase("<"))) {
								if (regex.matcher(currentLineSpecialChar.subSequence(j + 1, (j + 1) + 1)).find()) {
									bwWritingSpecialChars.write(" Position of "
											+ (String) currentLineSpecialChar.subSequence(j + 1, (j + 1) + 1)
											+ " is at " + ((j + 1) + 1));
									bwWritingSpecialChars.flush();
								}
								j++;
							}
						}
					}
				}
				/*
				 * } else if (MainHomePageForApplication.XmlTypeSelector ==
				 * "SOAP") { int ValueOfIndexForStaringSearch = 518; int j =
				 * ValueOfIndexForStaringSearch; for (int i =
				 * ValueOfIndexForStaringSearch; i < sCurrentLineSpecialChar
				 * .length(); i++) { System.out.println((String)
				 * sCurrentLineSpecialChar .substring(i, i + 1)); if
				 * (sCurrentLineSpecialChar.substring(i, i + 1)
				 * .equalsIgnoreCase(">")) { if
				 * (sCurrentLineSpecialChar.substring(i + 1, (i + 1) +
				 * 1).equalsIgnoreCase("<")) { System.out.println("Done 1"); }
				 * else { while (!(sCurrentLineSpecialChar.substring( j + 1, (j
				 * + 1) + 1) .equalsIgnoreCase("<"))) { if (regex.matcher(
				 * sCurrentLineSpecialChar .subSequence(j + 1, (j + 1) + 1))
				 * .find()) {
				 * bwWritingSpecialCharsToFileAfterSearchingForDataOrSpecialChars
				 * .write(" Position of " + (String) sCurrentLineSpecialChar
				 * .subSequence( j + 1, (j + 1) + 1) + " is at " + ((j + 1) +
				 * 1));
				 * bwWritingSpecialCharsToFileAfterSearchingForDataOrSpecialChars
				 * .flush(); } j++; } } } } }
				 */
			}
			if(fileWithXMLDataOnly.exists()){
				fileWithXMLDataOnly.delete();
			}
		} catch (IOException e) {
		}
	}
}
