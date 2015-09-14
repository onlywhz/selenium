package com.selenium.consts;

public enum BrowerType {

	IE("ie",1),FIREFOX("firefox",2),CHROME("chrome",3);
	
	private BrowerType(String code,Integer number){
		this.code=code;
		this.number=number;
	}
	private final String code;
	private final Integer number;
	public String getCode() {return code;}
	public Integer getNumber() {return number;}
	public static BrowerType find (String code){
		for (BrowerType enm : values()) {
			if (enm.getCode().equals(code)) {
				return enm;
			}
		}
		return null;
	}
}
