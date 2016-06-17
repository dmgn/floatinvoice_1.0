package com.floatinvoice.messages;

import org.springframework.web.multipart.MultipartFile;


public class UploadMessage extends BaseMsg{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MultipartFile file;
	
	public UploadMessage(){
		
	}
	
	public UploadMessage(MultipartFile multiPartFile){
		this.file = multiPartFile;
	}
	
	private String fileName;
	
	private String acronym;
	
	private String category;

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getAcronym() {
		return acronym;
	}

	public void setAcronym(String acronym) {
		this.acronym = acronym;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
