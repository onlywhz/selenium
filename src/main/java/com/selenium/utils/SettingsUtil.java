package com.selenium.utils;

import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.Dimension;

import com.selenium.consts.BrowerType;

public class SettingsUtil {
	public static final String OUTPUT_XLS_PATH = "webdriver.output.xls.path";
	public static final String WORK_IMG_HOME_KEY = "webdriver.work.img";
	public static final String XLS_FILE = ".xls";
	public static final String OUTPUT_XLS_FLG = "webdriver.output.xls.flag";
	// private static final String DB_NAME = "webdriver.db.name";
	// private static final String DB_PASSWORD = "webdriver.db.password";
	// private static final String DB_SAVE_PATH = "webdriver.db.save.path";
	// private static final String DB_USER = "webdriver.db.user";
	// private static final String DB_URL = "webdriver.db.url";
	private static String imgPath;
	private static String xlsPath;
	private static boolean xlsFlg;
	private static Properties settings;
	private static String testName;
	private static List<BrowerType> browerTypes;
	private static Dimension size;

	public static String getImgPath() {
		return imgPath;
	}

	public static String getXlsPath() {
		return xlsPath;
	}

	public static boolean isXlsFlg() {
		return xlsFlg;
	}

	static Properties getSettings() {
		return settings;
	}

	public static void init(Properties properties) {
		SlmTstUtil.initSysDate();
		xlsFlg = Boolean.valueOf(properties.getProperty(OUTPUT_XLS_FLG));
		settings = properties;
	}

	public static String getTestName() {
		return testName;
	}

	public static List<BrowerType> getBrowerTypes() {
		return browerTypes;
	}

	public static void setBrowerTypes(List<BrowerType> browerTypes) {
		SettingsUtil.browerTypes = browerTypes;
	}

	public static void init(String fileName) {
		if (fileName == null || StringUtils.EMPTY.equals(fileName)) {
			fileName = JOptionPane
					.showInputDialog("Please input an excel file name");
		}
		testName = fileName;
		SlmTstUtil.initSysDate();
		xlsPath = settings.getProperty(OUTPUT_XLS_PATH);
		imgPath = settings.getProperty(WORK_IMG_HOME_KEY);

		xlsPath = SlmTstUtil.encodePathString(xlsPath) + "\\" + testName + "_"
				+ SlmTstUtil.getSysDate();
		imgPath = SlmTstUtil.encodePathString(imgPath) + "\\" + testName + "_"
				+ SlmTstUtil.getSysDate();
	}

	public static void setSize(Dimension size) {
		SettingsUtil.size = size;

	}

	public static Dimension getSize() {
		return size;
	}

	// public static String getPw() {
	// return SettingsUtil.getSettings().getProperty(DB_PASSWORD);
	// }
	//
	// public static String getDBUrl() {
	// return SettingsUtil.getSettings().getProperty(DB_URL);
	// }
	//
	// public static String getDBName() {
	// return SettingsUtil.getSettings().getProperty(DB_NAME);
	// }
	//
	// public static String getOutputPath() {
	// return SettingsUtil.getSettings().getProperty(DB_SAVE_PATH);
	// }
	//
	// public static String getUser() {
	// return SettingsUtil.getSettings().getProperty(DB_USER);
	// }

}
