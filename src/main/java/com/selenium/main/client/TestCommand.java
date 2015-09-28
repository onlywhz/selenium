package com.selenium.main.client;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runners.model.InitializationError;
import org.openqa.selenium.WebDriver;

import com.selenium.tests.FncSeleniumTest;
import com.selenium.tests.imlp.SeleniumCommand;
import com.selenium.utils.SettingsUtil;
import com.selenium.utils.SlmTstUtil;


public abstract class TestCommand implements SeleniumCommand {
	@Test
	public final void test() throws InitializationError {
		SettingsUtil.init(this.setTestName());
		new FncSeleniumTest(this);
		assertTrue(SlmTstUtil.createDir(SettingsUtil.getImgPath()));
	}
	
	public void switchWindow(WebDriver driver){
		String[] handles=driver.getWindowHandles().toArray(new String[driver.getWindowHandles().size()]);
		driver.switchTo().window(handles[1]);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
