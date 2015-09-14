package org.junit.internal.builders;

import org.junit.internal.runners.SeleniumMethod;
import org.junit.runner.Runner;
import org.junit.runners.model.RunnerBuilder;

import com.selenium.tests.BeforeTest;

public class SeleniumBuilder extends RunnerBuilder {

	@Override
	public Runner runnerForClass(Class<?> each) throws Throwable {
		BeforeTest.getInstance("src/main/resources/settings.properties");
		if (hasSeleniumMethod(each))
			return new SeleniumMethod(each);
		return null;
	}

	public boolean hasSeleniumMethod(Class<?> testClass) {
		try {
			testClass.getMethod("selenium");
		} catch (NoSuchMethodException e) {
			return false;
		}
		return true;
	}

}
