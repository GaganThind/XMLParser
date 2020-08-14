package com.logtoxml.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.logtoxml.beans.DirectoryBean;
import com.logtoxml.selectingfiles.SelectingLogFileToReadXml;
import com.logtoxml.util.LogToXMLConstants;

public class MainHomePageForApplication {

	private static MainHomePageForApplication mainHomePageForApplication = null;
	
	private MainHomePageForApplication() { }

	public static MainHomePageForApplication getInstance() {
		
		// Thread safe Singleton instance
		if (null == mainHomePageForApplication) {
			Object mutex = new Object();
			synchronized(mutex) {
				mainHomePageForApplication = new MainHomePageForApplication();
			}
		}
		
		return mainHomePageForApplication;
	}

	// Button Area
	private JButton btnForSelectingLogFileinStarting;
	private JButton btnfindingSpecialCharInMainHome;
	private JButton btnParse;
	private JButton btnExit;

	private JTextField txtPathOfFileOnMainHomePage;

	private JFrame frmMainHomePage;

	private JPanel pnlMainHomePage;

	private DirectoryBean objVarConatiningAllData;

	private File fileWithXMLData;

	public void initializeMainPage() {
		initializeTxtPath();
		initializeFindSpecialChar();
		initializeParsing();
		initializeExit();
		initializeSelectLogFile();
		initialiseMainPagePanel();
		initializeMainPageForm();
	}

	private void initialiseMainPagePanel() {
		pnlMainHomePage = new JPanel();
		pnlMainHomePage.setLayout(null);
		pnlMainHomePage.setVisible(true);
		pnlMainHomePage.add(btnForSelectingLogFileinStarting);
		pnlMainHomePage.add(txtPathOfFileOnMainHomePage);
		pnlMainHomePage.add(btnfindingSpecialCharInMainHome);
		pnlMainHomePage.add(btnParse);
		pnlMainHomePage.add(btnExit);
	}

	private void initializeMainPageForm() {
		frmMainHomePage = new JFrame();
		frmMainHomePage.setSize(500, 400);
		frmMainHomePage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMainHomePage.setVisible(true);
		frmMainHomePage.add(pnlMainHomePage);
		frmMainHomePage.setLocationRelativeTo(null);
		frmMainHomePage.setTitle(LogToXMLConstants.TITLE);
	}

	private void initializeTxtPath() {
		txtPathOfFileOnMainHomePage = new JTextField();
		txtPathOfFileOnMainHomePage.setBounds(20, 50, 320, 30);
		txtPathOfFileOnMainHomePage.setEditable(false);
		txtPathOfFileOnMainHomePage.setVisible(true);
	}

	private void initializeExit() {
		btnExit = new JButton(LogToXMLConstants.BTN_QUIT);
		btnExit.setBounds(170, 320, 130, 30);
		btnExit.setVisible(true);
		btnExitListenerBinder();
	}

	/*
	 * Function binding button listener to exit the application
	 */
	private void btnExitListenerBinder() {
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	private void initializeSelectLogFile() {
		btnForSelectingLogFileinStarting = new JButton(LogToXMLConstants.BTN_FILE_SELECION);
		btnForSelectingLogFileinStarting.setBounds(350, 50, 125, 30);
		btnForSelectingLogFileinStarting.setEnabled(true);
		btnSelectorListenerBinder();
	}

	/*
	 * Function binding button listener to select the log file
	 */
	private void btnSelectorListenerBinder() {
		btnForSelectingLogFileinStarting.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				objVarConatiningAllData = (DirectoryBean) SelectingLogFileToReadXml.ReadLogFile();

				if (null != objVarConatiningAllData
						&& objVarConatiningAllData.getFileWithXmlAndGarbageData().exists()) {
					txtPathOfFileOnMainHomePage.setText(objVarConatiningAllData.getFullPathOfFile());
					btnParse.setEnabled(true);
					btnfindingSpecialCharInMainHome.setEnabled(false);
				}
			}
		});
	}

	private void initializeFindSpecialChar() {
		btnfindingSpecialCharInMainHome = new JButton(LogToXMLConstants.BTN_SPECIAL_CHAR_FINDING);
		btnfindingSpecialCharInMainHome.setBounds(250, 150, 180, 30);
		btnfindingSpecialCharInMainHome.setEnabled(false);
		btnSaveFileListenerBinder();
	}

	/*
	 * Function binding button listener to navigate to frame for saving the file
	 * that contains Special chars or Data based on Date
	 */
	private void btnSaveFileListenerBinder() {
		btnfindingSpecialCharInMainHome.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				WritingSpecialCharsToFile objWritingSpecialCharsToFile = new WritingSpecialCharsToFile();
				objWritingSpecialCharsToFile.writingSpecialCharsToFileAfterSearchingForDataOrSpecialChars(
						objVarConatiningAllData.getDirectoryPath(), fileWithXMLData);
			}
		});
	}

	private void initializeParsing() {
		btnParse = new JButton(LogToXMLConstants.BTN_FETCH_XML);
		btnParse.setBounds(80, 150, 130, 30);
		btnParse.setEnabled(false);
		btnParseLogFileListenerBinder();
	}

	/*
	 * Function binding button listener to parse log file to get xml
	 */
	private void btnParseLogFileListenerBinder() {
		btnParse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				WritingXmlFromLogFileToTxtFile objWritingXmlFromLogFileToTxtFile = new WritingXmlFromLogFileToTxtFile();
				try {
					fileWithXMLData = objWritingXmlFromLogFileToTxtFile.writeOnlyXmlToTxtFileFromLogFile(
							objVarConatiningAllData.getFileWithXmlAndGarbageData(),
							objVarConatiningAllData.getDirectoryPath());
				} catch (IOException | ParseException e1) {
				}

				btnParse.setEnabled(false);
				btnfindingSpecialCharInMainHome.setEnabled(true);
			}
		});
	}

}
