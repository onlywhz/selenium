package com.selenium.main.client;

import org.junit.runner.RunWith;
import org.junit.runners.Selenium;

import com.selenium.consts.BrowerType;


@RunWith(Selenium.class)
@Selenium.SuiteClasses(path="com.selenium.main.client.classes",bts={BrowerType.IPHONE})

public abstract class TestMain {
}
