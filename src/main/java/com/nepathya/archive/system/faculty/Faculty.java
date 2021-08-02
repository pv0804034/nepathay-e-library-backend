package com.nepathya.archive.system.faculty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tbl_faculty")
public class Faculty {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "faculty_id", updatable = false, nullable = false, length = 16)
	private Integer facultyId;
	
	@Column(name = "facultyName", nullable = false, updatable = true, unique = true)
	private String facultyName;
	
	public Faculty() {}
	
	public Faculty(String facultyName) {
		this.facultyName = facultyName;
	}

	public Integer getFacultyId() {
		return facultyId;
	}

	public void setFacultyId(Integer facultyId) {
		this.facultyId = facultyId;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}

	@Override
	public String toString() {
		return "Faculty [facultyId=" + facultyId + ", facultyName=" + facultyName + "]";
	}
	
	
	
}
