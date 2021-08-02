package com.nepathya.archive.system.docUploadNotice;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nepathya.archive.system.common.ResponseHandler;
import com.nepathya.archive.system.common.utils.GenericUtils;
import com.nepathya.archive.system.faculty.Faculty;
import com.nepathya.archive.system.faculty.FacultyRepository;
import com.nepathya.archive.system.security.user.User;
import com.nepathya.archive.system.security.user.UserRepository;
import com.nepathya.archive.system.service.common.LocaleService;

@Service
public class DocUploadNoticeService {
	
	@Autowired 
	FacultyRepository facultyRepository;
	
	@Autowired
	LocaleService localeService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	DocUploadNoticeRepository docUploadNoticeRepository;
	
	public ResponseEntity<?> add(DocUploadNoticeDTO docUploadNoticeDTO){
		Faculty faculty = facultyRepository.getById(docUploadNoticeDTO.getFacultyId());
		if(Objects.isNull(faculty)) {
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("faculty.not.found"));
		}
		
		User user = GenericUtils.getLoggedInUser();
		
		if(Objects.isNull(user)) {
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("user.not.found"));
		}
		
		DocUploadNotice docUploadNotice = new DocUploadNotice();
		
		docUploadNotice.setFaculty(faculty);
		docUploadNotice.setIsActive(docUploadNoticeDTO.getIsActive());
		docUploadNotice.setTitle(docUploadNoticeDTO.getTitle());
		docUploadNotice.setYear(docUploadNoticeDTO.getYear());
		docUploadNotice.setExpireDate(docUploadNoticeDTO.getExpireTime());
		docUploadNotice.setUser(user);
		
		docUploadNoticeRepository.save(docUploadNotice);
		return ResponseHandler.response(HttpStatus.OK, true, "Successfully added");
	}
	
	public ResponseEntity<?> getActive(){
		List<DocUploadNoticeResponse> response = new ArrayList<DocUploadNoticeResponse>();
		List<DocUploadNotice> list = docUploadNoticeRepository.findByIsActiveTrue();
		for(DocUploadNotice l : list) {
			System.out.println(l);
			if(l.getIsActive()) {
				DocUploadNoticeResponse r = new DocUploadNoticeResponse(l);
				response.add(r);
			}		
		}
		return ResponseHandler.response(HttpStatus.OK, true,response);
	}
	
	public ResponseEntity<?> getNonActive(){
		return ResponseHandler.response(HttpStatus.OK, true, docUploadNoticeRepository.findByIsActiveFalse());
	}
	
	public ResponseEntity<?> update(Integer id, DocUploadNoticeDTO docUploadNoticeDTO){
		DocUploadNotice docUploadNotice = docUploadNoticeRepository.getById(id);
		
		if(Objects.isNull(docUploadNotice)) {
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("notice.not.found"));
		}
		
		Faculty faculty = facultyRepository.getById(docUploadNoticeDTO.getFacultyId());
		if(Objects.isNull(faculty)) {
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("faculty.not.found"));
		}
		
		User user = GenericUtils.getLoggedInUser();
		
		if(Objects.isNull(user)) {
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("user.not.found"));
		}
		
		docUploadNotice.setExpireDate(docUploadNoticeDTO.getExpireTime());
		docUploadNotice.setFaculty(faculty);
		docUploadNotice.setUser(user);
		docUploadNotice.setIsActive(docUploadNoticeDTO.getIsActive());
		docUploadNotice.setTitle(docUploadNoticeDTO.getTitle());
		docUploadNotice.setYear(docUploadNoticeDTO.getYear());
		
		docUploadNoticeRepository.save(docUploadNotice);
		return ResponseHandler.response(HttpStatus.OK, true, "Successfully updated");
	}
}
