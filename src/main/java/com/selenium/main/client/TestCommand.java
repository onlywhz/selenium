package com.selenium.main.client;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

import com.selenium.tests.CreateXlsx;
import com.selenium.tests.FncSeleniumTest;
import com.selenium.tests.imlp.SeleniumCommand;
import com.selenium.utils.SettingsUtil;
import com.selenium.utils.SlmTstUtil;

public abstract class TestCommand implements SeleniumCommand {
	@Test
	public final void test() {
		SettingsUtil.init(this.setTestName());
		new FncSeleniumTest(this);
		assertTrue(SlmTstUtil.createDir(SettingsUtil.getImgPath()));
		if(SettingsUtil.getXlsFlg()){
			CreateXlsx cx=new CreateXlsx(SettingsUtil.getXlsPath());
			SlmTstUtil.setCx(cx);
		}
	}
	
}
