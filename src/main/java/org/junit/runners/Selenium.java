package org.junit.runners;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.internal.builders.AllDefaultPossibilitiesBuilder;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

import com.selenium.consts.BrowerType;
import com.selenium.main.client.TestCommand;
import com.selenium.tests.AfterTest;
import com.selenium.utils.SettingsUtil;

public class Selenium extends ParentRunner<Runner> {

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	@Inherited
	public @interface SuiteClasses {
		/**
		 * the path to be run
		 */
		public String path();

		/**
		 * the BrowerTypes to be run
		 */
		public BrowerType[] bts();
	}

	private final List<Runner> fRunners;

	public Selenium(Class<?> klass, Class<?>[] suiteClasses)
			throws InitializationError {
		this(new AllDefaultPossibilitiesBuilder(true), klass, suiteClasses);
	}

	public Selenium(Class<?> klass, List<Runner> runners)
			throws InitializationError {
		super(klass);
		fRunners = runners;
	}

	public Selenium(Class<?> klass, RunnerBuilder builder)
			throws InitializationError, ClassNotFoundException {
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

	private static Class<?>[] getTestClasses(Class<?> klass)
			throws InitializationError, ClassNotFoundException {
		SuiteClasses annotation = klass.getAnnotation(SuiteClasses.class);
		if (annotation == null)
			throw new InitializationError(String.format(
					"class '%s' must have a SuiteClasses annotation",
					klass.getName()));
		List<Class<?>> suiteClasses = new ArrayList<Class<?>>();
		List<BrowerType> browerTypes=Arrays.asList(annotation.bts());
		SettingsUtil.setBrowerTypes(browerTypes);
		List<Class<?>> testCommands = getAllClasses(annotation.path(),false);
		for(Class<?> c:testCommands){
			List<Class<?>> suites = new ArrayList<Class<?>>();
			suites.add(c);
			for(BrowerType bt: BrowerType.typeAll()){
				if(browerTypes.contains(bt)){
					suites.add(bt.getTestClass());
				}
			}
			suites.add(AfterTest.class);
			suiteClasses.addAll(suites);
		}
		Class<?>[] classes=new Class<?>[]{};
		return suiteClasses.toArray(classes);
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

	/**
	 * get all classes from a package path
	 * 
	 * @param packageName
	 * @param childPackage
	 * @return
	 */
	private static List<Class<?>> getAllClasses(String packageName,
			boolean childPackage) {
		List<Class<?>> classes = null;
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		String packagePath = packageName.replace(".", "/");
		URL url = loader.getResource(packagePath);
		if (url != null) {
			classes = getClassNameByFile(url.getPath(), null, childPackage);
		}
		return classes;
	}

	private static List<Class<?>> getClassNameByFile(String filePath,
			List<Class<?>> className, boolean childPackage) {
		List<Class<?>> myClassName = new ArrayList<Class<?>>();
		File file = new File(filePath);
		File[] childFiles = file.listFiles();
		for (File childFile : childFiles) {
			if (childFile.isDirectory()) {
				if (childPackage) {
					myClassName.addAll(getClassNameByFile(childFile.getPath(),
							myClassName, childPackage));
				}
			} else {
				String childFilePath = childFile.getPath();
				if (childFilePath.endsWith(".class")) {
					try {
						childFilePath = childFilePath.substring(
								childFilePath.indexOf("\\classes") + 9,
								childFilePath.lastIndexOf("."));
						childFilePath = childFilePath.replace("\\", ".");
						Class<?> klass = Class.forName(childFilePath);
						if (TestCommand.class.isAssignableFrom(klass))
							myClassName.add(klass);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return myClassName;
	}
}
