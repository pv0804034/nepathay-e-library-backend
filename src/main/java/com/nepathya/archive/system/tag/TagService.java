package com.nepathya.archive.system.tag;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nepathya.archive.system.common.ResponseHandler;
import com.nepathya.archive.system.service.common.LocaleService;

@Service
public class TagService {

	@Autowired
	TagRepository tagRepository;
	
	@Autowired
	private LocaleService localeService;
	
	public ResponseEntity<?> add(TagDTO tagDTO){
		Tag tag = new Tag(tagDTO.getTagName());
		tagRepository.save(tag);
		return ResponseHandler.response(HttpStatus.OK, true, localeService.getMessage("tag.created"));
	}
	
	public ResponseEntity<?> update(Integer tagId, TagDTO tagDTO){
		Tag tag = tagRepository.findByTagId(tagId);
		
		if (Objects.isNull(tag))
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("tag.not.found"));
		
		tag.setTagName(tagDTO.getTagName());
		tagRepository.save(tag);
		return ResponseHandler.response(HttpStatus.OK, true , localeService.getMessage("tag.update"));
	}
	
	public ResponseEntity<?> getById(Integer tagId){
		
		Tag tag = tagRepository.findByTagId(tagId);
		if (Objects.isNull(tag))
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("tag.not.found"));
		
		return ResponseHandler.response(HttpStatus.OK, true , tag);
	}
	
	public ResponseEntity<?> getAll(){
		return ResponseHandler.response(HttpStatus.OK, true , tagRepository.findAll());
	}
}
