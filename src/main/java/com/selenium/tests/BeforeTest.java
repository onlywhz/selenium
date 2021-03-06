package com.selenium.tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.selenium.utils.SettingsUtil;

public class BeforeTest {
	private volatile static BeforeTest beforeTest;
	private BeforeTest(String settingsString) {
		Properties properties = new Properties();
		try {
			FileInputStream settingsFile = new FileInputStream(settingsString);
			properties.load(settingsFile);
			settingsFile.close();
			SettingsUtil.init(properties);
			setEnv("src/main/resources/env.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void setEnv(String envString) throws FileNotFoundException,
			IOException {
		Properties properties = new Properties();

		FileInputStream envFile = new FileInputStream(envString);
		properties.load(envFile);
		envFile.close();
		Properties sysproperties = System.getProperties(); // system properties
		properties.putAll(sysproperties);
		System.setProperties(properties);
	}

	public static BeforeTest getInstance(String filePath) {
		if (beforeTest == null) {
			synchronized (BeforeTest.class) {
				if (beforeTest == null) {
					beforeTest = new BeforeTest(filePath);
				}
			}
		}

		return beforeTest;
	}
}
