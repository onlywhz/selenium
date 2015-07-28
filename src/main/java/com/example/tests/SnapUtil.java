package com.example.tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class SnapUtil {
	private static int filenum=1;
	private static String sysDate;
	public static final String CHROME_DRIVER_HOME_VAULE="D:\\Projects\\selenium-ide\\chromedriver\\chromedriver.exe"; 
	public static final String CHROME_DRIVER_HOME_KEY="webdriver.chrome.driver";
	public static final String IE_DRIVER_HOME_VAULE="D:\\Projects\\selenium-ide\\IEDriverServer\\IEDriverServer.exe"; 
	public static final String IE_DRIVER_HOME_KEY="webdriver.ie.driver";
	
	static {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		sysDate=df.format(new Date());
	}
	
	/**
	 * 
	 * @param drivername
	 * Example:SnapUtil.snapshot((TakesScreenshot)driver);
	 */
	public static void snapshot(TakesScreenshot drivername)
	  {
	    String parentPath = "D:\\Projects\\selenium-ide\\pictures\\";
	    File workDir=new File(parentPath+sysDate);
	    workDir.deleteOnExit();
		workDir.mkdir();
	    File scrFile = drivername.getScreenshotAs(OutputType.FILE);
	        try {
	        	File desFile=new File(workDir.getPath()+"\\"+filenum+".png");
	            System.out.println("save snapshot path is:"+desFile.getPath());
	            FileUtils.copyFile(scrFile, desFile);
	            filenum++;
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            System.out.println("Can't save screenshot");
	            e.printStackTrace();
	        } 
	        finally
	        {
	            System.out.println("screen shot finished");
	        }
	  }
	
	//ignore Protected Mode
    public static void ignoreeProtectedMode(){    
        System.setProperty(IE_DRIVER_HOME_KEY, IE_DRIVER_HOME_VAULE);
        DesiredCapabilities dc = DesiredCapabilities.internetExplorer();
        dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        dc.setVersion("7");
    }
}
