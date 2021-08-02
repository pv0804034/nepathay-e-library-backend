package com.nepathya.archive.system.document;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.nepathya.archive.system.docUploadNotice.DocUploadNotice;
import com.nepathya.archive.system.faculty.Faculty;
import com.nepathya.archive.system.security.user.User;
import com.nepathya.archive.system.tag.Tag;

@Entity
@Table(name = "tble_document")
public class Document {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "document_id", updatable = false, nullable = false, length = 16)
	private Integer documentId;
	
	@Column(name = "title", updatable = true, nullable = false)
	private String title;
	
	@Column(name = "discription", columnDefinition="TEXT", updatable = true, nullable = false)
	private String discription;
	
	@Column(name = "type", updatable = true, nullable = false)
	private Type type;
	
	@Column (name = "year", updatable = true, nullable = false)
	private int year;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_user_document", joinColumns = @JoinColumn(name = "document_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> authors;
	
	@JoinColumn(name = "faculty_id", nullable = false)
	@OneToOne(targetEntity = Faculty.class,fetch = FetchType.LAZY)
	private Faculty faculty;
	
	@Column(name = "author_semester", nullable = false)
	private int semester;
	
	@JoinColumn(name="doc_notic_id", nullable = false)
	@OneToOne(targetEntity = DocUploadNotice.class, fetch = FetchType.LAZY)
	private DocUploadNotice docUploadNotice;
	
	@Column(name = "is_visible")
	private boolean isVisible = false;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "tbl_document_tag",joinColumns = @JoinColumn(name = "document_id"), inverseJoinColumns = @JoinColumn(name = "tag_id"))
	private List<Tag> tag = new ArrayList<Tag>();
	
	@Column(name = "fileurl", nullable = false)
	private String fileUrl;
	
	public Document() {}
	
	

	public Document(String title, String discription, Type type, int year, List<User> authors, Faculty faculty,
			int semester, DocUploadNotice docUploadNotice, boolean isVisible, List<Tag> tag, String fileUrl) {
		this.title = title;
		this.discription = discription;
		this.type = type;
		this.year = year;
		this.authors = authors;
		this.faculty = faculty;
		this.semester = semester;
		this.docUploadNotice = docUploadNotice;
		this.isVisible = isVisible;
		this.tag = tag;
		this.fileUrl = fileUrl;
	}



	public Integer getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public List<Tag> getTag() {
		return tag;
	}

	public void setTag(List<Tag> tag) {
		this.tag = tag;
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

	public List<User> getAuthors() {
		return authors;
	}

	public void setAuthors(List<User> authors) {
		this.authors = authors;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}


	public DocUploadNotice getDocUploadNotice() {
		return docUploadNotice;
	}


	public void setDocUploadNotice(DocUploadNotice docUploadNotice) {
		this.docUploadNotice = docUploadNotice;
	}
	
}
