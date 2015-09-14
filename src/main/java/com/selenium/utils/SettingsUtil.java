package com.selenium.utils;

import java.util.Properties;

public class SettingsUtil {
	public static final String OUTPUT_XLSX_PATH = "webdriver.output.xlsx.path";
	public static final String WORK_IMG_HOME_KEY = "webdriver.work.img";
	public static final String ENV_PATH = "webdriver.evn.path";
	public static final String TEST_BROWER_TYPE = "webdriver.test.brower.type";
	public static final String XLSX_FILE=".xlsx";
	private static String imgPath;
	private static String xlsxPath;
	private static String xlsxFile;
	private static String envPath;
	private static String browerTypes;
	
	public static String getImgPath() {
		return imgPath;
	}

	public static String getXlsxPath() {
		return xlsxPath;
	}

	public static String getXlsxFile() {
		return xlsxFile;
	}

	public static void setXlsxFile(String xlsxFile) {
		SettingsUtil.xlsxFile = xlsxFile;
	}

	public static String getEnvPath() {
		return envPath;
	}
	
	public static String getBrowerTypes() {
		return browerTypes;
	}

	public static void init(Properties properties) {
		xlsxPath=String.valueOf(properties.get(OUTPUT_XLSX_PATH));
		imgPath=String.valueOf(properties.get(WORK_IMG_HOME_KEY));
		envPath=SlmTstUtil.encodePathString(String.valueOf(properties.get(ENV_PATH)));
		browerTypes=String.valueOf(properties.get(TEST_BROWER_TYPE));
		xlsxPath = SlmTstUtil.encodePathString(xlsxPath) + "\\"
				+ SlmTstUtil.getSysDate();
		imgPath = SlmTstUtil.encodePathString(imgPath) + "\\"
				+ SlmTstUtil.getSysDate();
	}
	
}
