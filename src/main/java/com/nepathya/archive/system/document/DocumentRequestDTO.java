package com.nepathya.archive.system.document;

import java.util.List;
import java.util.UUID;


public class DocumentRequestDTO {

	private String title;
	
	private String discription;
	
	private String type;
	
	private int year;
	
	private List<UUID> authors;
	
	private Integer facultyId;
	
	private int semester;
	
	private List<Integer> tags;
	
	private String fileUrl;
	
	private boolean visible;
	
	private Integer docUploadNoticeId;
	
	public DocumentRequestDTO() {}
	

	public DocumentRequestDTO(String title, String discription, String type, int year, List<UUID> authors,
			Integer facultyId, int semester, List<Integer> tags, String fileUrl, boolean isVisible,
			Integer docUplaodNoticeId) {
		this.title = title;
		this.discription = discription;
		this.type = type;
		this.year = year;
		this.authors = authors;
		this.facultyId = facultyId;
		this.semester = semester;
		this.tags = tags;
		this.fileUrl = fileUrl;
		this.visible = isVisible;
		this.docUploadNoticeId = docUplaodNoticeId;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Integer getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(Integer facultyId) {
		this.facultyId = facultyId;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public List<Integer> getTags() {
		return tags;
	}

	public void setTags(List<Integer> tags) {
		this.tags = tags;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}


	public String getFileUrl() {
		return fileUrl;
	}


	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public List<UUID> getAuthors() {
		return authors;
	}


	public void setAuthors(List<UUID> authors) {
		this.authors = authors;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}


	public Integer getDocUploadNoticeId() {
		return docUploadNoticeId;
	}


	public void setDocUploadNoticeId(Integer docUploadNoticeId) {
		this.docUploadNoticeId = docUploadNoticeId;
	}


	@Override
	public String toString() {
		return "DocumentRequestDTO [title=" + title + ", discription=" + discription + ", type=" + type + ", year="
				+ year + ", authors=" + authors + ", facultyId=" + facultyId + ", semester=" + semester + ", tags="
				+ tags + ", fileUrl=" + fileUrl + ", isVisible=" + visible + ", docUploadNoticeId="
				+ docUploadNoticeId + "]";
	}


	


	
	
	
}
