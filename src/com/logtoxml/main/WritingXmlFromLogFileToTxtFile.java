package com.logtoxml.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;

import com.logtoxml.util.LogToXMLConstants;

public class WritingXmlFromLogFileToTxtFile {

	public File writeOnlyXmlToTxtFileFromLogFile(File InitialFile, String DirectoryPath)
			throws IOException, ParseException {
		File file = new File(DirectoryPath, LogToXMLConstants.FILE_XML);
		File fileFinalResult = new File(DirectoryPath, LogToXMLConstants.FILE_FINAL_RESULT);
		if(fileFinalResult.exists()){
			fileFinalResult.delete();
		}
		if (file.exists()) {
			file.delete();
		} else {
			file.createNewFile();
		}
		try (BufferedReader brWriteXmlToTxtFile = new BufferedReader(new FileReader(InitialFile));
				FileWriter fwWriteXmlToTxtFile = new FileWriter(file);
				BufferedWriter bwWriteXmlToTxtFile = new BufferedWriter(fwWriteXmlToTxtFile);) {

			String sCurrentLine = null;

			while ((sCurrentLine = brWriteXmlToTxtFile.readLine()) != null) {
				bwWriteXmlToTxtFile.write(sCurrentLine);
				bwWriteXmlToTxtFile.write("\n");
			}
		}
		return file;
	}
}
