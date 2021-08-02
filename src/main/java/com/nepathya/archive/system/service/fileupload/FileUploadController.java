package com.nepathya.archive.system.service.fileupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nepathya.archive.system.common.ResponseHandler;
import com.nepathya.archive.system.constant.UrlConstant;

import io.swagger.annotations.Api;

@Api
@RestController
public class FileUploadController {
	
	@Autowired
	private BucketService bucketService;
	
	@PostMapping(UrlConstant.FILE_UPLOAD_SINGLE)
	public ResponseEntity<?> singleFileUpload( @RequestPart("file") MultipartFile file, @RequestHeader String authorization) throws Exception{
		System.out.println("this is haribol" + file);
		return ResponseHandler.response(HttpStatus.OK, true, bucketService.uploadFile(file));
		
	}
	

}
