package com.selenium.main.client.classes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.selenium.main.client.TestCommand;
import com.selenium.utils.SnapImg;

public class Test2 extends TestCommand{

	@Override
	public void testCode(WebDriver driver, String baseUrl, SnapImg snapImg) {
		baseUrl = "https://www.baidu.com/";
	    driver.get(baseUrl + "/");
	    snapImg.snapshot();
	    driver.findElement(By.id("kw")).click();
	    snapImg.snapshot();
	    driver.findElement(By.id("kw")).clear();
	    driver.findElement(By.id("kw")).sendKeys("selenium 教程");
	    snapImg.snapshot();
	    driver.findElement(By.id("su")).click();
	    snapImg.snapshot();
	}

	@Override
	public String setTestName() {
		// TODO Auto-generated method stub
		return "test2";
	}
	
}
