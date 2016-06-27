package com.floatinvoice.common;

public enum EnquiryStatusEnum {

	NEW(0, "NEW"),
	CLOSED(1, "CLOSED"),
	PENDING(2, "PENDING"),
	STAGED(3, "STAGED"),
	RELEASED(4, "RELEASED");
	
	int code;
	String text;
	
	EnquiryStatusEnum(int code, String text){
		this.code = code;
		this.text = text;
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
