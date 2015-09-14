package com.selenium.tests;

import static org.junit.Assert.assertTrue;

import javax.swing.JOptionPane;

import org.junit.Test;

import com.selenium.utils.SettingsUtil;
import com.selenium.utils.SlmTstUtil;

public class CreateXlsx {

	@Test
	public void createDirAndFile() throws Exception {
		String fileName = JOptionPane
				.showInputDialog("Please input an excel file name");

		assertTrue("excel can't create",
				SlmTstUtil.createFile(SettingsUtil.getXlsxPath(),
						(fileName + SettingsUtil.XLSX_FILE)));
		SettingsUtil.setXlsxFile(SettingsUtil.getXlsxPath() + "\\" + fileName
				+ SettingsUtil.XLSX_FILE);
	}

	public void createDirAndFile(String fileName) throws Exception {
		SlmTstUtil.createFile(SettingsUtil.getXlsxPath(),
				(fileName + SettingsUtil.XLSX_FILE));
		SettingsUtil.setXlsxFile(SettingsUtil.getXlsxPath() + "\\" + fileName
				+ SettingsUtil.XLSX_FILE);
	}
}
