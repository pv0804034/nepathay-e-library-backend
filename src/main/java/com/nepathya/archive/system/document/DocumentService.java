package com.nepathya.archive.system.document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nepathya.archive.system.common.ResponseHandler;
import com.nepathya.archive.system.docUploadNotice.DocUploadNotice;
import com.nepathya.archive.system.docUploadNotice.DocUploadNoticeRepository;
import com.nepathya.archive.system.faculty.Faculty;
import com.nepathya.archive.system.faculty.FacultyRepository;
import com.nepathya.archive.system.security.user.User;
import com.nepathya.archive.system.security.user.UserRepository;
import com.nepathya.archive.system.service.common.LocaleService;
import com.nepathya.archive.system.service.fileupload.BucketService;
import com.nepathya.archive.system.service.fileupload.FileMetaDataService;
import com.nepathya.archive.system.tag.Tag;
import com.nepathya.archive.system.tag.TagRepository;

@Service
public class DocumentService {

		@Autowired 
		DocumentRepository documentRepository;
		
		@Autowired 
		LocaleService localeService;
		
		@Autowired
		FileMetaDataService fileMetaDataService;
		
		@Autowired
		UserRepository userRepository;
		
		@Autowired
		FacultyRepository facultyRepository;
		
		@Autowired
		TagRepository tagRepository;
		
		@Autowired
		BucketService bucketService;
		
		@Autowired
		DocUploadNoticeRepository docUploadNoticeRepository;
		
		public ResponseEntity<?> add(DocumentRequestDTO documentDTO){
			System.out.println("this is haribol");
			System.out.println(documentDTO.toString());
			Faculty faculty = facultyRepository.findByFacultyId(documentDTO.getFacultyId());
			
			if(Objects.isNull(faculty)) {
				System.out.println("hair bol " + documentDTO.getFacultyId());
				return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("faculty.not.found"));
			}
			
			List<Tag> tags = new ArrayList<Tag>();
			
			for(int i = 0;i<documentDTO.getTags().size();i++) {
				Tag tag = tagRepository.findByTagId(documentDTO.getTags().get(i));
				if(Objects.isNull(tag)) {
					return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("tag.not.found"));
				}
				tags.add(tag);
			}
			
			List<User> users = new ArrayList<User>();
			for(UUID id : documentDTO.getAuthors()) {
				User user = userRepository.findByUserId(id);
				if(Objects.isNull(user)) {
					return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("user.not.found"));
				}
				users.add(user);
			}
			
			DocUploadNotice docNotice = docUploadNoticeRepository.getById(documentDTO.getDocUploadNoticeId());
			
			if(Objects.isNull(docNotice)) {
				return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("doc.notice.not.found"));
			}
			
			Document document = new Document(
						documentDTO.getTitle(),
						documentDTO.getDiscription(),
						documentDTO.getType().toLowerCase().equals("project")?Type.PROJECT:Type.PURPOSAL,
						documentDTO.getYear(),
						users,
						faculty,
						documentDTO.getSemester(),
						docNotice,
						documentDTO.isVisible(),
						tags,
						documentDTO.getFileUrl()
					);
			documentRepository.save(document);
			
			return ResponseHandler.response(HttpStatus.OK, true, localeService.getMessage("document.created"));
		}
		public ResponseEntity<?> getAll(){
			List<Document> list = documentRepository.findAll();
			List<DocumentResponseDTO> response = new ArrayList<>();
			for(Document d : list) {
				DocumentResponseDTO dto = new DocumentResponseDTO(d);
				response.add(dto);
			}
			return ResponseHandler.response(HttpStatus.OK, true, response);
		}
		
		public ResponseEntity<?> getDocumentById(Integer documentId){
			Document d = documentRepository.getById(documentId);
			if(Objects.isNull(d) || !d.isVisible()) {
				return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("document.not.found"));
			}
			return ResponseHandler.response(HttpStatus.OK, true, new DocumentResponseDTO(d));
		}
		
		public ResponseEntity<?> getByYear(int year){
			List<Document> list = documentRepository.findByYear(year);
			List<DocumentResponseDTO> response = new ArrayList<>();
			for(Document d : list) {
				if(d.isVisible()) {
					DocumentResponseDTO dto = new DocumentResponseDTO(d);
					response.add(dto);
				}
					
			}
			return ResponseHandler.response(HttpStatus.OK, true, response);
		}
		                  
		public ResponseEntity<?> getByfacultyAndYear(Integer faculty, int year){
			
			Faculty fac = facultyRepository.findByFacultyId(faculty);
			if(Objects.isNull(fac)) {
				return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("faculty.not.found"));
			}
			List<Document> list = documentRepository.findByFacultyAndYear(fac, year);
			List<DocumentResponseDTO> response = new ArrayList<>();
			for(Document d : list) {
				if(d.isVisible()) {
					DocumentResponseDTO dto = new DocumentResponseDTO(d);
					response.add(dto);
				}
			}
			return ResponseHandler.response(HttpStatus.OK, true, response);
		}
		
		public ResponseEntity<?> getByTagId(Integer tagId){
			Tag tag = tagRepository.findByTagId(tagId);
			if(Objects.isNull(tag)) {
				return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("tag.not.found"));
			}
			List<Document> list = documentRepository.findByTag(tag);
			List<DocumentResponseDTO> response = new ArrayList<>();
			for(Document d : list) {
				if(d.isVisible()) {
					DocumentResponseDTO dto = new DocumentResponseDTO(d);
					response.add(dto);
				}
			}
			return ResponseHandler.response(HttpStatus.OK, true, response);
		}
		
		public ResponseEntity<?> updateDocument(Integer documentId, DocumentRequestDTO documentDTO){
			Document d = documentRepository.getById(documentId);
			if(Objects.isNull(d)) {
				return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("document.not.found"));
			}
			
			Faculty faculty = facultyRepository.findByFacultyId(documentDTO.getFacultyId());
			
			if(Objects.isNull(faculty)) {
				return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("faculty.not.found"));
			}
			
			List<Tag> tags = new ArrayList<Tag>();
			
			for(int i = 0;i<documentDTO.getTags().size();i++) {
				Tag tag = tagRepository.findByTagId(documentDTO.getTags().get(i));
				if(Objects.isNull(tag)) {
					return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("tag.not.found"));
				}
				tags.add(tag);
			}
			
			List<User> users = new ArrayList<User>();
			for(UUID id : documentDTO.getAuthors()) {
				User user = userRepository.findByUserId(id);
				if(Objects.isNull(user)) {
					return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("user.not.found"));
				}
				users.add(user);
			}
			
			DocUploadNotice docNotice = docUploadNoticeRepository.getById(documentDTO.getDocUploadNoticeId());
			
			if(Objects.isNull(docNotice)) {
				return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("doc.notice.not.found"));
			}
			
			d.setAuthors(users);
			d.setDiscription(documentDTO.getDiscription());
			d.setTitle(documentDTO.getTitle());
			d.setType(documentDTO.getType().toLowerCase().equals("project")?Type.PROJECT:Type.PURPOSAL);
			d.setYear(documentDTO.getYear());
			d.setFaculty(faculty);
			d.setTag(tags);
			d.setFileUrl(documentDTO.getFileUrl());
			d.setSemester(documentDTO.getSemester());
			d.setDocUploadNotice(docNotice);
			documentRepository.save(d);
			
			return ResponseHandler.response(HttpStatus.OK, true, localeService.getMessage("document.updated"));
		}
		
		public ResponseEntity<?> makeVisible(Integer documentId){
			Document d = documentRepository.getById(documentId);
			if(Objects.isNull(d)) {
				return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("document.not.found"));
			}
			d.setVisible(!d.isVisible());
			documentRepository.save(d);
			return ResponseHandler.response(HttpStatus.OK, true, localeService.getMessage("document.updated"));
		}
		
		public ResponseEntity<?> delete(Integer documentId){
			Document d = documentRepository.getById(documentId);
			if(Objects.isNull(d)) {
				return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("document.not.found"));
			}
			
			documentRepository.delete(d);
			return ResponseHandler.response(HttpStatus.OK, true, localeService.getMessage("Successfully deleted"));
		}
		
		public ResponseEntity<?> getByLinkIdAndAuthor(Integer linkId, UUID authorId){
			DocUploadNotice docUploadNotice = docUploadNoticeRepository.getById(linkId);
			if(Objects.isNull(docUploadNotice)) {
				return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("doc.link.not.found"));
			}
			
			User user = userRepository.findByUserId(authorId);
			if(Objects.isNull(docUploadNotice)) {
				return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,localeService.getMessage("user.not.found"));
			}
			
			Document d = documentRepository.findByDocUploadNoticeAndAuthors(docUploadNotice, user);
			
			if(Objects.isNull(d)) {
				return ResponseHandler.response(HttpStatus.OK, true, null);
			}
			
			DocumentResponseDTO documentResponseDTO = new DocumentResponseDTO(d);
			
			return ResponseHandler.response(HttpStatus.OK, true, documentResponseDTO);
		}
}
