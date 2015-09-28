package com.selenium.tests;


import org.openqa.selenium.firefox.FirefoxDriver;

import com.selenium.consts.BrowerType;
import com.selenium.utils.SettingsUtil;

public class TestFirefox extends ATest {
	@Override
	public void setUp() throws Exception {
		browerType = BrowerType.FIREFOX;
		driver = new FirefoxDriver();
		// driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		SettingsUtil.setSize(driver.manage().window().getSize());
	}
}
