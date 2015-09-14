package com.selenium.main.client;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.selenium.tests.BeforeTest;
import com.selenium.tests.imlp.SeleniumCommand;
import com.selenium.tests.imlp.XlsxCommand;
import com.selenium.utils.SlmTstUtil;
import com.selenium.utils.SnapImg;

public class TestInit {

	
	@Test
	public void test() {
		SlmTstUtil.setSc(new TestCommand());
	}

}
class TestCommand implements SeleniumCommand,XlsxCommand{

	@Override
	public void testCode(WebDriver driver, String baseUrl,SnapImg snapImg) {
		baseUrl = "https://www.baidu.com/";
	    driver.get(baseUrl + "/");
	    driver.findElement(By.id("kw")).click();
	    driver.findElement(By.id("kw")).clear();
	    driver.findElement(By.id("kw")).sendKeys("selenium 教程");
	    driver.findElement(By.id("su")).click();
	}

	@Override
	public String setXlsxName() {
		
		return "1";
	}
	
}
