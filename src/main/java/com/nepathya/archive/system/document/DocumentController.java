package com.nepathya.archive.system.document;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nepathya.archive.system.common.ErrorCollection;
import com.nepathya.archive.system.common.ResponseHandler;
import com.nepathya.archive.system.constant.UrlConstant;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping(UrlConstant.DOCUMENT)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class DocumentController {
	
	@Autowired 
	DocumentService documentService;

	
	@PostMapping
	public ResponseEntity<?> add(@RequestBody DocumentRequestDTO documentDTO, BindingResult bindingResult,@RequestHeader String authorization){
		System.out.println("haribol");
		if (bindingResult.hasErrors()) {
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false, ErrorCollection.getErrorMap(bindingResult));
		}
		return documentService.add(documentDTO);
	}
	
	@GetMapping
	public ResponseEntity<?> getAll(){
		return documentService.getAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getById(@RequestParam Integer documentId){
		return documentService.getDocumentById(documentId);
	}
	
	@RequestMapping(value = "/{tag}", method = RequestMethod.GET)
	public ResponseEntity<?> getByTag(@RequestParam(value="tag") Integer tagId){
		return documentService.getByTagId(tagId);
	}
	
	@RequestMapping(value = "/{year}", method = RequestMethod.GET)
	public ResponseEntity<?> getByYear(@RequestParam(value="year") int year){
		return documentService.getByYear(year);
	}
	
	@GetMapping("/{linkId}/{authorId}")
	public ResponseEntity<?> getByLinkIdAndAuthor(@RequestParam Integer linkId, @RequestParam UUID authorId,@RequestHeader String authorization){
//		System.out.println(linkId + " " + authorId);
		return documentService.getByLinkIdAndAuthor(linkId,authorId);
	}
	
	@GetMapping("/{faculty}/{year}")
	public ResponseEntity<?> getByFacultyAndYear(@RequestParam Integer faculty, @RequestParam int year){
		return documentService.getByfacultyAndYear(faculty,year);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateDocument(@RequestParam Integer documentId,@RequestBody DocumentRequestDTO documentDTO, @RequestHeader String authorization){
		System.out.println(documentDTO.getAuthors());
		return documentService.updateDocument(documentId,documentDTO);
		
	}
	
	@RequestMapping(value = "/{documentId}", method = RequestMethod.PUT)
	public ResponseEntity<?> makeVisible(@RequestParam(value="documentId") Integer documentId,@RequestHeader String authorization){
		return documentService.makeVisible(documentId);
	}
	
	@RequestMapping(value = "/{documentId}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@RequestParam(value="documentId") Integer documentId, @RequestHeader String authorization){
		return documentService.delete(documentId);
	}
	
	
}
 