package com.nepathya.archive.system.docUploadNotice;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nepathya.archive.system.faculty.Faculty;
import com.nepathya.archive.system.security.user.User;

@Entity
@Table(name="tbl_doc_upload_notice")
public class DocUploadNotice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false, length = 16)
	private Integer id;
	
	@Column(name="year", nullable=false)
	private Integer year;
	
	@JoinColumn(name = "faculty_id", nullable = false)
	@OneToOne(targetEntity = Faculty.class,fetch = FetchType.EAGER)
	private Faculty faculty;
	
	@JoinColumn(name = "author_id", nullable = false)
	@OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
	private User user;
	
	@JsonIgnore
	@CreationTimestamp
	@Column(name = "created_date", nullable = false)
	private LocalDateTime createdDate;
	
	@Column(name="is_active", nullable=false)
	private Boolean isActive;
	
	@Column(name="title", nullable=false)
	private String title;
	
	@JsonIgnore
	@Column(name = "expire_date", nullable = false)
	private LocalDateTime expireDate;
    
    public DocUploadNotice() {}
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDateTime expireDate) {
		this.expireDate = expireDate;
	}

	@Override
	public String toString() {
		return "DocUploadNotice [id=" + id + ", year=" + year + ", faculty=" + faculty + ", user=" + user
				+ ", createdDate=" + createdDate + ", isActive=" + isActive + ", title=" + title + ", expireDate="
				+ expireDate + "]";
	}
	
}
