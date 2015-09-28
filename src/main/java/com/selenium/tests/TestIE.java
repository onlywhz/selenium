package com.selenium.tests;


import org.openqa.selenium.ie.InternetExplorerDriver;

import com.selenium.consts.BrowerType;
import com.selenium.utils.SettingsUtil;

public class TestIE extends ATest {
	@Override
	public void setUp() throws Exception {
		browerType = BrowerType.IE;
		ignoreeProtectedMode();
		driver = new InternetExplorerDriver();
		// driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		SettingsUtil.setSize(driver.manage().window().getSize());
	}
}
