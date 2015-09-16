package com.selenium.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.ie.InternetExplorerDriver;

import com.selenium.consts.BrowerType;

public class TestIE extends ATest {
	@Override
	public void setUp() throws Exception {
		browerType=BrowerType.IE;
		ignoreeProtectedMode();
		driver = new InternetExplorerDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
}
