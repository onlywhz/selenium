package com.selenium.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.selenium.consts.BrowerType;

public class SnapImg {
	private int filenum;
	private TakesScreenshot drivername;
	private String imgPath;
	
	public SnapImg(TakesScreenshot drivername, BrowerType browerType) {
		this(drivername);
		imgPath=SettingsUtil.getImgPath()+"\\"+browerType.getCode();
	}
	public SnapImg(TakesScreenshot drivername) {
		this.drivername = drivername;
		this.filenum=1;
		imgPath=SettingsUtil.getImgPath();
	}
	/**
	 * 
	 * @param drivername
	 *            Example:SnapUtil.snapshot((TakesScreenshot)driver);
	 */
	public void snapshot() {
		File imgDir = new File(imgPath);
		File scrFile = drivername.getScreenshotAs(OutputType.FILE);
		try {
			// create directory
			if(!imgDir.isDirectory()){
				imgDir.mkdirs();
			}
			// create png
			File desFile = new File(imgPath + "\\" + filenum
					+ ".png");
			FileUtils.copyFile(scrFile, desFile);
			filenum++;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		
		}
	}

}
