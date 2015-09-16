package com.selenium.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.selenium.tests.CreateXlsx;
import com.selenium.tests.imlp.SeleniumCommand;

public class SlmTstUtil {
	private static String sysDate;
	private static SeleniumCommand sc;
	private static CreateXlsx cx;
	
	public static void initSysDate() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		sysDate = df.format(new Date());
	}

	public static boolean createDir(String... dirPaths) {
		StringBuilder sb = new StringBuilder();
		for (String path : dirPaths) {
			sb.append(path);
		}
		if (sb.length() == 0) {
			return false;
		}
		File workDir = new File(sb.toString());
		workDir.deleteOnExit();
		return workDir.mkdirs();
	}

	public static boolean createFile(String dirPath, String fileName)
			throws Exception {
		File workDir = new File(dirPath);
		if (!workDir.isDirectory()) {
			return false;
		}
		if (fileName == null) {
			return false;
		}
		File file = new File(dirPath + "\\" + fileName);
		return file.createNewFile();
	}

	public static String encodePathString(String dirPath) {
		if (dirPath == null) {
			return null;
		}
		String result=dirPath;
		String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf("windows") > -1) {
			result=dirPath.replace("/", "\\");
		}
		return result;
	}

	public static String getSysDate() {
		return sysDate;
	}

	public static SeleniumCommand getSc() {
		return sc;
	}

	public static void setSc(SeleniumCommand sc) {
		SlmTstUtil.sc = sc;
	}

	public static CreateXlsx getCx() {
		return cx;
	}

	public static void setCx(CreateXlsx cx) {
		SlmTstUtil.cx = cx;
	}

	public static void setSysDate(String sysDate) {
		SlmTstUtil.sysDate = sysDate;
	}
}
