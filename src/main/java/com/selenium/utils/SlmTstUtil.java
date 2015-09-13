package com.selenium.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.selenium.main.TestMain;

public class SlmTstUtil {
	private static String sysDate;
	public static final String WORK_PATH_HOME_KEY = "webdriver.work.path";
	public static final String WORK_IMG_HOME_KEY = "webdriver.work.img";
	private static String imgPath;
	private static String workPath;
	private static String workFile;

	static {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		sysDate = df.format(new Date());
	}

	public static boolean createDir(String... dirPaths) throws Exception {
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
		String os = System.getProperty("os.name").toLowerCase();
		if (os.indexOf("windows") > 0) {
			dirPath.replace("/", "\\");
		}
		return dirPath;
	}

	public static String getSysDate() {
		return sysDate;
	}

	public static String getImgPath() {
		return imgPath;
	}
	
	public static void setImgPath(String imgPath) {
		SlmTstUtil.imgPath = imgPath;
	}

	public static String getWorkPath() {
		return workPath;
	}

	public static void setWorkPath(String workPath) {
		SlmTstUtil.workPath = workPath;
	}

	public static String getWorkFile() {
		return workFile;
	}

	public static void setWorkFile(String workFile) {
		SlmTstUtil.workFile = workFile;
	}
	
	
}
