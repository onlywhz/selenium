package org.junit.runners;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

public class Selenium extends ParentRunner<Runner> {
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	@Inherited
	public @interface SuitePath {
		/**
		 * @return the classes to be run
		 */
		public String value();
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	@Inherited
	public @interface SuiteClasses {
		/**
		 * @return the classes to be run
		 */
		public Class<?>[] value();
	}
	private final List<Runner> fRunners;
	public Selenium(Class<?> klass, Class<?>[] suiteClasses)
			throws InitializationError {
		this(new AllDefaultPossibilitiesBuilder(true), klass, suiteClasses);
	}

	public Selenium(Class<?> klass, List<Runner> runners)
			throws InitializationError {
		super(klass);
		fRunners=runners;
	}

	public Selenium(Class<?> klass, RunnerBuilder builder)
			throws InitializationError {
		this(builder, klass, getTestClasses(klass));
	}

	public Selenium(RunnerBuilder builder, Class<?> klass,
			Class<?>[] suiteClasses) throws InitializationError {
		this(klass, builder.runners(klass, suiteClasses));
		
	}

	public Selenium(RunnerBuilder builder, Class<?>[] classes)
			throws InitializationError {
		this(null, builder.runners(null, classes));
	}
	
	private static Class<?>[] getTestClasses(Class<?> klass) throws InitializationError {
		SuiteClasses annotation1= klass.getAnnotation(SuiteClasses.class);
		if (annotation1 == null)
			throw new InitializationError(String.format("class '%s' must have a SuiteClasses annotation", klass.getName()));
		return annotation1.value();
	}
	
	@Override
	protected List<Runner> getChildren() {
		return fRunners;
	}
	
	@Override
	protected Description describeChild(Runner child) {
		return child.getDescription();
	}

	@Override
	protected void runChild(Runner runner, final RunNotifier notifier) {
		runner.run(notifier);
	}
}
