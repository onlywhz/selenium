package com.selenium.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

import com.selenium.consts.BrowerType;
import com.selenium.utils.SettingsUtil;

public class TestIphone extends ATest {

	@Override
	public void setUp() throws Exception {
		browerType=BrowerType.IPHONE;
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("general.useragent.override","Mozilla/5.0 (iPhone; CPU iPhone OS 8_0 like Mac OS X) AppleWebKit/600.1.3 (KHTML, like Gecko) Version/8.0 Mobile/12A4345d Safari/600.1.4");
		driver = new FirefoxDriver(profile);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		Dimension dimension=new Dimension(375,667);
		driver.manage().window().setSize(dimension);
		SettingsUtil.setSize(dimension);
	}

	
}
