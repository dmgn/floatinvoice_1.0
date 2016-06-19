package com.floatinvoice.business;

import com.floatinvoice.business.dao.FileServiceDao;
import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.ByteMsg;
import com.floatinvoice.messages.UploadMessage;

public class FileServiceImpl implements FileService {

	FileServiceDao fileServiceDao;
	
	public FileServiceImpl(){
		
	}
	
	public FileServiceImpl(FileServiceDao fileServiceDao){
		this.fileServiceDao = fileServiceDao;
	}
	@Override
	public ByteMsg download(String refId) {
		byte [] fileBytes = fileServiceDao.download(refId);
		ByteMsg resultBytes = new ByteMsg(fileBytes);
		return resultBytes;
	}

	@Override
	public ByteMsg downloadLenderAgreement(String lenderAcronym, String refId) {
		return new ByteMsg(fileServiceDao.downloadLenderAgreement(lenderAcronym, refId));
	}

	@Override
	public BaseMsg uploadLenderAgreement(UploadMessage msg) throws Exception {
		return fileServiceDao.uploadLenderAgreement(msg);
	}

	@Override
	public ByteMsg downloadSupportDocs(String refId) {
		byte [] fileBytes = fileServiceDao.downloadSupportDocs(refId);
		ByteMsg resultBytes = new ByteMsg(fileBytes);
		return resultBytes;
	}

}
