package com.example.tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class SnapUtil {
	private static int filenum=1;
	private static String sysDate;
	static {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		sysDate=df.format(new Date());
	}
	public static void snapshot(TakesScreenshot drivername)
	  {
	    String parentPath = "D:\\Projects\\selenium-ide\\pictures\\";
	    File workDir=new File(parentPath+sysDate);
	    workDir.deleteOnExit();
		workDir.mkdir();
	    System.out.println(workDir.getPath());
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
}
