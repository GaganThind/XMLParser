package com.logtoxml.selectingfiles;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import com.logtoxml.beans.DirectoryBean;

public class SelectingLogFileToReadXml {
	
	private static DirectoryBean objVar;

	public static Object ReadLogFile() {
		JFileChooser chooserForReadingLogFile = new JFileChooser();
		String directoryPath1;
		String fullPathOfFile1;
		File fileWithXmlAndGarbageData;
		
		int returnValue = chooserForReadingLogFile.showOpenDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			try {
				objVar = new DirectoryBean();
				fileWithXmlAndGarbageData = chooserForReadingLogFile.getSelectedFile();
				directoryPath1 = fileWithXmlAndGarbageData.getCanonicalPath().substring(0,
							fileWithXmlAndGarbageData.getCanonicalPath().lastIndexOf(File.separator));
				fullPathOfFile1 = fileWithXmlAndGarbageData.getAbsolutePath();
	
				objVar.setFileWithXmlAndGarbageData(fileWithXmlAndGarbageData);
				objVar.setDirectoryPath(directoryPath1);
				objVar.setFullPathOfFile(fullPathOfFile1);
			} catch (IOException e) {
			}
		} /*else if (returnValue == JFileChooser.CANCEL_OPTION) {}*/
		return objVar;
	}
}
