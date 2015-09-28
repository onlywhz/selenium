package com.selenium.tests;

import static org.junit.Assert.fail;


import java.awt.Robot;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.selenium.consts.BrowerType;
import com.selenium.utils.SlmTstUtil;
import com.selenium.utils.SnapImg;

public abstract class ATest {

	protected WebDriver driver;
	protected String baseUrl;
	protected boolean acceptNextAlert = true;
	protected StringBuffer verificationErrors = new StringBuffer();
	protected BrowerType browerType;
	protected SnapImg snapImg;
	protected boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	protected boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	protected String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

	// ignore Protected Mode
	public void ignoreeProtectedMode() {
		DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
		dc.setCapability(
				InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
				true);
		dc.setVersion("9");
	}
	@Before
	public abstract void setUp() throws Exception;
	
	@Test
	public void test() throws Exception {
		snapImg=new SnapImg(driver,browerType);
		moveInit();
		SlmTstUtil.getSc().testCode(driver, baseUrl,snapImg);
		Thread.sleep(5000);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}

	}
	
	 private void moveInit(){  
         try{
         Robot robot = new Robot();
         robot.mouseMove(0,0);
         }catch(Exception e){
             e.printStackTrace();
         }
    } 
	
}
