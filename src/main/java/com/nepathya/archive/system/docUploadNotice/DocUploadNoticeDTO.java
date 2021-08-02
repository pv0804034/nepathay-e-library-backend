package com.nepathya.archive.system.docUploadNotice;


import java.time.LocalDateTime;

public class  DocUploadNoticeDTO{
	
	private Integer year;
	
	private Integer facultyId;
	
	private String title;
	
	private LocalDateTime expireTime;
	
	Boolean isActive;
	
	public DocUploadNoticeDTO() {}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(Integer facultyId) {
		this.facultyId = facultyId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public LocalDateTime getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}
	
}
