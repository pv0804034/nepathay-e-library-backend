package com.nepathya.archive.system.docUploadNotice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DocUploadNoticeRepository extends JpaRepository<DocUploadNotice, Integer> {
	
	List<DocUploadNotice> findByIsActiveTrue();
	
	List<DocUploadNotice> findByIsActiveFalse();
	
}
