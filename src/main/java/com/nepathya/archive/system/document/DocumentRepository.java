package com.nepathya.archive.system.document;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nepathya.archive.system.docUploadNotice.DocUploadNotice;
import com.nepathya.archive.system.faculty.Faculty;
import com.nepathya.archive.system.security.user.User;
import com.nepathya.archive.system.tag.Tag;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
	
	List<Document> findByYear(int year);
	
	List<Document> findByFaculty(Faculty faculty);
	
	List<Document> findByFacultyAndYear(Faculty faculty,int year);
	
	List<Document> findByTag(Tag tag);
	
	Document findByDocUploadNoticeAndAuthors(DocUploadNotice docUploadNotice, User user);
}