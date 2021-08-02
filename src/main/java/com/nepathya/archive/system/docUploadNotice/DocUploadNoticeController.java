package com.nepathya.archive.system.docUploadNotice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nepathya.archive.system.common.ErrorCollection;
import com.nepathya.archive.system.common.ResponseHandler;
import com.nepathya.archive.system.constant.UrlConstant;
import com.nepathya.archive.system.service.common.LocaleService;

import io.swagger.annotations.Api;

@Api
@RequestMapping(UrlConstant.DOCNOTICE)
@RestController
public class DocUploadNoticeController {
	
	@Autowired
	LocaleService localeService;
	
	@Autowired
	DocUploadNoticeService docUploadNoticeService;
	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody DocUploadNoticeDTO docUploadNoticeDTO,@RequestHeader String authorization, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,ErrorCollection.getErrorMap(bindingResult));
		}
		return docUploadNoticeService.add(docUploadNoticeDTO);
	}
	
	@GetMapping("/active")
	public ResponseEntity<?> getActive(@RequestHeader String authorization){
		return docUploadNoticeService.getActive();
	}
	
	@GetMapping("/inactive")
	public ResponseEntity<?> getIsActive(@RequestHeader String authorization){
		return docUploadNoticeService.getNonActive();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@RequestParam Integer id, @RequestBody DocUploadNoticeDTO docUploadNoticeDTO, @RequestHeader String authorization, BindingResult bindingResult){
		if(bindingResult.hasErrors()) {
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,ErrorCollection.getErrorMap(bindingResult));
		}
		return docUploadNoticeService.update(id,docUploadNoticeDTO);
	}
}
