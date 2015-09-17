package com.selenium.tests;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;
import com.selenium.utils.CreateXls;
import com.selenium.utils.SettingsUtil;


public class AfterTest {

	@Test
	public  void createXls() throws FileNotFoundException, IOException {
		if (SettingsUtil.isXlsFlg()) {
			CreateXls cx = new CreateXls(SettingsUtil.getXlsPath());
			cx.addImgs(SettingsUtil.getBrowerTypes());
		}
	}
}
