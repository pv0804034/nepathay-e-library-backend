package com.nepathya.archive.system.docUploadNotice;

import java.time.LocalDateTime;
import java.util.UUID;

public class DocUploadNoticeResponse {
	
	private Integer id;
	
	private String faculty;
	
	private UUID authorId;
	
	private String author;
	
	private LocalDateTime expiretime;
	
	private Boolean isActive;
	
	private LocalDateTime createdDate;
	
	private String title;
	
	private Integer year;
	
	public DocUploadNoticeResponse() {}
	
	public DocUploadNoticeResponse(DocUploadNotice d) {
		this.id = d.getId();
		this.faculty = d.getFaculty().getFacultyName();
		this.authorId = d.getUser().getUserId();
		this.author = d.getUser().getFirstName() +" " + d.getUser().getLastName();
		this.expiretime = d.getExpireDate();
		this.isActive = d.getIsActive();
		this.createdDate = d.getCreatedDate();
		this.title = d.getTitle();
		this.year = d.getYear();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public UUID getAuthorId() {
		return authorId;
	}

	public void setAuthorId(UUID authorId) {
		this.authorId = authorId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getExpiretime() {
		return expiretime;
	}

	public void setExpiretime(LocalDateTime expiretime) {
		this.expiretime = expiretime;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
}
