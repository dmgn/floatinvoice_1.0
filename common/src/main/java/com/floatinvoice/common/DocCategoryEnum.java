package com.floatinvoice.common;

public enum DocCategoryEnum {


	IDPROOF(1, "IDPROOF"),
	ADDRESSPROOF(2, "ADDRESSPROOF" ),
	TAXRETURNS(3, "TAXRETURNS" ),
	OTHERS(4, "OTHERS");
	
	int code;
	String name;
	
	DocCategoryEnum( int code,
	String name ){
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
