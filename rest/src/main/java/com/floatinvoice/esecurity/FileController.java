package com.floatinvoice.esecurity;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.floatinvoice.business.FileService;
import com.floatinvoice.messages.BaseMsg;
import com.floatinvoice.messages.ByteMsg;
import com.floatinvoice.messages.UploadMessage;

@Controller
public class FileController {

	@Autowired
	FileService fileService;
	
	@RequestMapping(value = { "/downloadSupportDocs"}, method = RequestMethod.GET)
    public  void downloadSupportDocs(HttpServletResponse response, 
    		@RequestParam(value="refId", required=true) String refId,
    		@RequestParam(value="fileName", required=true) String fileName,
    		@RequestParam(value="acro", required=false) String acro,
    		@RequestParam(value="type", required=true) String type) throws IOException {
        ByteMsg byteMsg = fileService.downloadSupportDocs(refId);
        byte [] bytes = byteMsg.getBytes();
        response.setContentType(getContentType(type));
        response.setHeader("Content-Disposition", String.format("attachment;filename=\"%s\"", fileName));
        ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
        out.flush();
        out.close();
    }
	
	@RequestMapping(value = { "/download"}, method = RequestMethod.GET)
    public  void download(HttpServletResponse response, 
    		@RequestParam(value="refId", required=true) String refId,
    		@RequestParam(value="fileName", required=true) String fileName,
    		@RequestParam(value="acro", required=true) String acro,
    		@RequestParam(value="type", required=true) String type) throws IOException {
        ByteMsg byteMsg = fileService.download(refId);
        byte [] bytes = byteMsg.getBytes();
        response.setContentType(getContentType(type));
        response.setHeader("Content-Disposition", String.format("attachment;filename=\"%s\"", fileName));
        ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
        out.flush();
        out.close();
    }
	
	@RequestMapping(value = { "/download/agreement"}, method = RequestMethod.GET)
    public  void downloadAgreement(HttpServletResponse response, 
    		@RequestParam(value="refId", required=true) String refId,
    		@RequestParam(value="fileName", required=true) String fileName,
    		@RequestParam(value="acro", required=true) String acro,
    		@RequestParam(value="type", required=true) String type) throws IOException {
        ByteMsg byteMsg = fileService.downloadLenderAgreement(acro, refId);
        byte [] bytes = byteMsg.getBytes();
        response.setContentType(getContentType(type));
        response.setHeader("Content-Disposition", String.format("attachment;filename=\"%s\"", fileName));
        ServletOutputStream out = response.getOutputStream();
        out.write(bytes);
        out.flush();
        out.close();
    }
	
	
	@RequestMapping(value = { "/upload/agreement"}, method = RequestMethod.POST)
    public  ResponseEntity<BaseMsg> uploadFile(@RequestParam(value="acro", required=true) String acro,
    		@RequestParam(value="filename", required=false) String fileName,
    		@RequestParam(value="category", required=true) String category,
    		@RequestParam(value="file", required=true) MultipartFile file) throws Exception {    
		UploadMessage uploadMsg = new UploadMessage(file);
    	uploadMsg.setFileName(file.getOriginalFilename());
    	uploadMsg.setAcronym(acro);
    	return new ResponseEntity<>(fileService.uploadLenderAgreement(uploadMsg), HttpStatus.OK);
	}
	
	
	private String getContentType(String type){
		
		String retVal = null;
		ContentType [] array = ContentType.values();
		for(int i=0; i< array.length; i++){
			if(array[i].name().equalsIgnoreCase(type)){
				retVal = array[i].getContentType();
				break;
			}
		}
		return retVal;
	}
	
	enum ContentType{
		
		xls("application/vnd.ms-excel"),
		xlsx("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
		pdf("application/pdf");
		String contentType;
		
		ContentType(String contentType) {
			this.contentType = contentType;
		}

		public String getContentType() {
			return contentType;
		}

		public void setContentType(String contentType) {
			this.contentType = contentType;
		}
		
		
	}
}
