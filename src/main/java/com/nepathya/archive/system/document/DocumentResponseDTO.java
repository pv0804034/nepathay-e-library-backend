package com.nepathya.archive.system.document;

import java.util.ArrayList;
import java.util.List;

import com.nepathya.archive.system.constant.FileUrlConstant;
import com.nepathya.archive.system.security.user.User;
import com.nepathya.archive.system.security.user.UserResponseDto;
import com.nepathya.archive.system.tag.Tag;

public class DocumentResponseDTO {
	
	private Integer id;
	
	private List<UserResponseDto> authors = new ArrayList<UserResponseDto>();
	
	private String faculty;
	
	private int year;
	
	private List<Tag> tags = new ArrayList<Tag>();
	
	private String fileUrl;
	
	private int semester;
	
	private String title;
	
	private String discription;
	
	private Type type;
	
	private boolean isVisible;

	public DocumentResponseDTO() {}
	
	public DocumentResponseDTO(Document document) {
		
		this.id = document.getDocumentId();
		for(User u:document.getAuthors()) {
			this.authors.add(new UserResponseDto(u));
		}
		this.faculty = document.getFaculty().getFacultyName();
		this.year = document.getYear();
		for(Tag tag : document.getTag()) {
			tags.add(tag);
		}
		this.fileUrl = FileUrlConstant.BASEURL + document.getFileUrl();
		this.semester = document.getSemester();
		this.title = document.getTitle();
		this.discription = document.getDiscription();
		this.type = document.getType();
		this.isVisible = document.isVisible();
		
	}


	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDiscription() {
		return discription;
	}

	public void setDiscription(String discription) {
		this.discription = discription;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getYear() {
		return year;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public List<UserResponseDto> getAuthors() {
		return authors;
	}

	public void setAuthors(List<UserResponseDto> authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		return "DocumentResponseDTO [id=" + id + ", authors=" + authors + ", faculty=" + faculty + ", year=" + year
				+ ", tags=" + tags + ", fileUrl=" + fileUrl + ", semester=" + semester + ", title=" + title
				+ ", discription=" + discription + ", type=" + type + ", isVisible=" + isVisible + "]";
	}
	
	
	
}
