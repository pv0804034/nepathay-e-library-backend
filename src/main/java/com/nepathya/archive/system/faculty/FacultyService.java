package com.nepathya.archive.system.faculty;

import java.util.Objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nepathya.archive.system.common.ResponseHandler;
import com.nepathya.archive.system.service.common.LocaleService;

@Service
public class FacultyService {

	@Autowired
	FacultyRepository facultyRepository;
	
	@Autowired
	private LocaleService localeService;
	
	public ResponseEntity<?> add(FacultyDTO facultyDTO){
		Faculty faculty = new Faculty(facultyDTO.getFacultyName());
		facultyRepository.save(faculty);
		return ResponseHandler.response(HttpStatus.OK, true, localeService.getMessage("faculty.created"));
	}
	
	public ResponseEntity<?> update(Integer facultyId, FacultyDTO facultyDTO){
		Faculty faculty = facultyRepository.findByFacultyId(facultyId);
		
		if (Objects.isNull(faculty))
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("faculty.not.found"));
		
		faculty.setFacultyName(facultyDTO.getFacultyName());
		facultyRepository.save(faculty);
		return ResponseHandler.response(HttpStatus.OK, true , localeService.getMessage("faculty.update"));
	}
	
	public ResponseEntity<?> getById(Integer facultyId){
		
		Faculty faculty = facultyRepository.findByFacultyId(facultyId);
		if (Objects.isNull(faculty))
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("faculty.not.found"));
		
		return ResponseHandler.response(HttpStatus.OK, true , faculty);
	}
	
	public ResponseEntity<?> getAll(){
		return ResponseHandler.response(HttpStatus.OK, true , facultyRepository.findAll());
	}
}
