package com.selenium.tests;

import org.openqa.selenium.chrome.ChromeDriver;

import com.selenium.consts.BrowerType;
import com.selenium.utils.SettingsUtil;

public class TestChrome extends ATest {
	@Override
	public void setUp() throws Exception {
		browerType=BrowerType.CHROME;
		driver = new ChromeDriver();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		SettingsUtil.setSize(driver.manage().window().getSize());
		
	}

}
