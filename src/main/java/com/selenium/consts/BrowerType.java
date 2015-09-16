package com.selenium.consts;

import com.selenium.tests.ATest;
import com.selenium.tests.TestChrome;
import com.selenium.tests.TestFirefox;
import com.selenium.tests.TestIE;
import com.selenium.tests.TestIphone;

public enum BrowerType {

	IE("ie",1,TestIE.class),FIREFOX("firefox",2,TestFirefox.class),CHROME("chrome",3,TestChrome.class),IPHONE("iphone",4,TestIphone.class);
	
	private BrowerType(String code,Integer number,Class<? extends ATest> testClass){
		this.code=code;
		this.number=number;
		this.testClass= testClass;
	}
	private final String code;
	private final Integer number;
	private final Class<? extends ATest> testClass;
	public String getCode() {return code;}
	public Integer getNumber() {return number;}
	public Class<? extends ATest> getTestClass() {return testClass;}
	public static BrowerType find (String code){
		for (BrowerType enm : values()) {
			if (enm.getCode().equals(code)) {
				return enm;
			}
		}
		return null;
	}
	public static BrowerType[] typeAll(){
		return values();
	}
}
