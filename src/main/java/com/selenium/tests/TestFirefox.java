package com.selenium.tests;

import java.util.concurrent.TimeUnit;

import org.junit.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.selenium.consts.BrowerType;

public class TestFirefox extends ATest {
	@Override
	public void setUp() throws Exception {
		browerType=BrowerType.FIREFOX;
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
}
