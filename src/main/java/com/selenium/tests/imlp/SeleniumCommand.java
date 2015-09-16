package com.selenium.tests.imlp;

import org.openqa.selenium.WebDriver;

import com.selenium.utils.SnapImg;

public interface SeleniumCommand {

	public void testCode(WebDriver driver,String baseUrl, SnapImg snapImg);
	public String setTestName();
}
