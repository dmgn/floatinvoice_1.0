package com.floatinvoice.common;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


public enum IndustryTypeEnum {

	MANUFACTURING(0, "Manufacturing"),
	FISHING(1, "Fishing");
	
	int code;
	String text;
	
	IndustryTypeEnum(int code, String text){
		this.code = code;
		this.text = text;
	}
	
	private static final Map<Integer,IndustryTypeEnum> lookup = new HashMap<>();

	static {
	     for(IndustryTypeEnum s : EnumSet.allOf(IndustryTypeEnum.class))
	          lookup.put(s.getCode(), s);
	}
	
	public static IndustryTypeEnum get(int code) { 
	      return lookup.get(code); 
	}
	
	

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
