package com.selenium.utils;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.selenium.consts.BrowerType;

public class SnapImg {
	private int filenum;
	private WebDriver driver;
	private String imgPath;
	private BrowerType browerType;

	public SnapImg(WebDriver driver, BrowerType browerType) {
		this(driver);
		imgPath = SettingsUtil.getImgPath() + "\\" + browerType.getCode();
		this.browerType=browerType;
	}

	public SnapImg(WebDriver driver) {
		this.driver = driver;
		this.filenum = 1;
		imgPath = SettingsUtil.getImgPath();
	}

	/**
	 * 
	 * @param drivername
	 *            Example:SnapUtil.snapshot((TakesScreenshot)driver);
	 */
	public void snapFullShot() {
		File imgDir = new File(imgPath);
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			// create directory
			if (!imgDir.isDirectory()) {
				imgDir.mkdirs();
			}
			// create png
			File desFile = new File(imgPath + "\\" + filenum + ".png");
			// BufferedImage img=ImageIO.read(scrFile);
			// int h=img.getHeight()/2;
			// int w=img.getWidth()/2;
			// BufferedImage image = new BufferedImage(w,
			// h,BufferedImage.TYPE_4BYTE_ABGR );
			// ImageIO.setUseCache(false);
			// image.getGraphics().drawImage(img, 0, 0,w , h, null);
			// ImageIO.write(image, "png", desFile);
			FileUtils.copyFile(scrFile, desFile);
			filenum++;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}

	public void snapshot(By by) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView();",driver.findElement(by));
		this.snapshot();
	}

	public void snapshot() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if(browerType.equals(BrowerType.CHROME)){
			this.snapFullShot();
			return;
		}
		try {
			File imgDir = new File(imgPath);
			if (!imgDir.isDirectory()) {
				imgDir.mkdirs();
			}
			Robot robot = new Robot();
			File desFile = new File(imgPath + "\\" + filenum + ".png");
			BufferedImage bi = robot.createScreenCapture(new Rectangle(
					SettingsUtil.getSize().getWidth()-10, SettingsUtil.getSize()
							.getHeight()-10));
			ImageIO.write(bi, "png", desFile);
			filenum++;
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
