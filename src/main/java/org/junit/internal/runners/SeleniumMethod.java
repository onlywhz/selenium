package org.junit.internal.runners;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import junit.framework.Test;

public class SeleniumMethod extends JUnit38ClassRunner {
	public SeleniumMethod(Class<?> klass) throws Throwable {
		super(testFromSeleniumMethod(klass));
	}

	public static Test testFromSeleniumMethod(Class<?> klass) throws Throwable {
		Method seleniumMethod= null;
		Test selenium= null;
		try {
			seleniumMethod= klass.getMethod("selenium");
			if (! Modifier.isStatic(seleniumMethod.getModifiers())) {
				throw new Exception(klass.getName() + ".selenium() must be static");
			}
			selenium= (Test) seleniumMethod.invoke(null); // static method
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
		return selenium;
	}
}
