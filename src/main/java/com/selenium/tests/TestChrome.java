package com.selenium.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;

import com.selenium.consts.BrowerType;

public class TestChrome extends ATest {
	@Override
	public void setUp() throws Exception {
		browerType=BrowerType.CHROME;
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

}
