package com.selenium.utils;

import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.commons.lang3.StringUtils;

public class SettingsUtil {
	public static final String OUTPUT_XLS_PATH = "webdriver.output.xls.path";
	public static final String WORK_IMG_HOME_KEY = "webdriver.work.img";

	public static final String XLS_FILE = ".xls";
	public static final String OUTPUT_XLS_FLG = "webdriver.output.xls.flag";
	private static String imgPath;
	private static String xlsPath;
	private static boolean xlsFlg;
	private static Properties settings;
	private static String testName;

	public static String getImgPath() {
		return imgPath;
	}

	public static String getXlsPath() {
		return xlsPath;
	}

	public static boolean getXlsFlg() {
		return xlsFlg;
	}

	public static void init(Properties properties) {
		SlmTstUtil.initSysDate();
		xlsFlg = Boolean.valueOf(String.valueOf(properties
				.get(OUTPUT_XLS_FLG)));
		settings = properties;
	}

	public static String getTestName() {
		return testName;
	}

	public static void init(String fileName) {
		if (fileName == null || StringUtils.EMPTY.equals(fileName)) {
			fileName = JOptionPane
					.showInputDialog("Please input an excel file name");
		}
		testName=fileName;
		SlmTstUtil.initSysDate();
		xlsPath = String.valueOf(settings.get(OUTPUT_XLS_PATH));
		imgPath = String.valueOf(settings.get(WORK_IMG_HOME_KEY));

		xlsPath = SlmTstUtil.encodePathString(xlsPath) + "\\"+testName+"_"
				+ SlmTstUtil.getSysDate();
		imgPath = SlmTstUtil.encodePathString(imgPath) + "\\"+testName+"_"
				+ SlmTstUtil.getSysDate();
	}

}
