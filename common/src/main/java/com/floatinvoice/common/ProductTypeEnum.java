package com.floatinvoice.common;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


public enum ProductTypeEnum {

	INVOICE_FINANCING(1, "Invoice Financing"),
	EXPORT_FACTORING(2, "Export Factoring"),
	DOMESTIC_FACTORING(3, "Domestic Factoring");
	
	int code;
	String text;
	
	ProductTypeEnum(int code, String text){
		this.code = code;
		this.text = text;
	}
	
	private static final Map<Integer,ProductTypeEnum> lookup = new HashMap<>();

	static {
	     for(ProductTypeEnum s : EnumSet.allOf(ProductTypeEnum.class))
	          lookup.put(s.getCode(), s);
	}
	
	public static ProductTypeEnum get(int code) { 
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
