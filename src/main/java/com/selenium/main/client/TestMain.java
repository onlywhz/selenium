package com.selenium.main.client;
import org.junit.runner.RunWith;
import org.junit.runners.Selenium;


import org.junit.runners.Suite;

import com.selenium.tests.BeforeTest;
import com.selenium.tests.TestFirefox;
import com.selenium.tests.TestChrome;
import com.selenium.tests.TestIE;


@RunWith(Suite.class)
@Suite.SuiteClasses({ BeforeTest.class, TestFirefox.class, TestChrome.class,
		TestIE.class })
public class TestMain{	
}
