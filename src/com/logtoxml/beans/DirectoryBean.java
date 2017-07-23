package com.logtoxml.beans;

import java.io.File;


public class DirectoryBean {
	
	private File fileWithXmlAndGarbageData;
	private String directoryPath;
	private String fullPathOfFile;

	public File getFileWithXmlAndGarbageData() {
		return fileWithXmlAndGarbageData;
	}

	public void setFileWithXmlAndGarbageData(File fileWithXmlAndGarbageData) {
		this.fileWithXmlAndGarbageData = fileWithXmlAndGarbageData;
	}

	public String getDirectoryPath() {
		return directoryPath;
	}

	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}

	public String getFullPathOfFile() {
		return fullPathOfFile;
	}

	public void setFullPathOfFile(String fullPathOfFile) {
		this.fullPathOfFile = fullPathOfFile;
	}

}
