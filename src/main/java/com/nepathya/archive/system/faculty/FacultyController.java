package com.nepathya.archive.system.faculty;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nepathya.archive.system.common.ErrorCollection;
import com.nepathya.archive.system.common.ResponseHandler;
import com.nepathya.archive.system.constant.UrlConstant;

import io.swagger.annotations.Api;

@Api
@RequestMapping(UrlConstant.FACULTY)
@RestController
public class FacultyController {
	
	@Autowired
	FacultyService facultyService;
	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody FacultyDTO facultyDTO, BindingResult bindingResult,
			@RequestHeader String authorization){
		if (bindingResult.hasErrors()) {
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false, ErrorCollection.getErrorMap(bindingResult));
		}
		return facultyService.add(facultyDTO);
	}
	
	@PutMapping(UrlConstant.BY_ID)
	public ResponseEntity<?> update(@PathVariable("id") Integer facultyId,@RequestBody FacultyDTO facultyDTO, BindingResult bindingResult,
			@RequestHeader String authorization){
		if (bindingResult.hasErrors()) {
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false, ErrorCollection.getErrorMap(bindingResult));
		}
		return facultyService.update(facultyId,facultyDTO);
	}
	
	@GetMapping(UrlConstant.BY_ID)
	public ResponseEntity<?> getById(@PathVariable("id") Integer facultyId, BindingResult bindingResult){
		if (bindingResult.hasErrors()) {
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false, ErrorCollection.getErrorMap(bindingResult));
		}
		return facultyService.getById(facultyId);
	}
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		
		return facultyService.getAll();
		
	}
}
