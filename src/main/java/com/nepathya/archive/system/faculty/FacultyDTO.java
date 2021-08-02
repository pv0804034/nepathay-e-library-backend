package com.nepathya.archive.system.faculty;

public class FacultyDTO {
	
	private String facultyName;
	
	public FacultyDTO() {}
	
	public FacultyDTO(String facultyName) {
		this.facultyName = facultyName;
	}

	public String getFacultyName() {
		return facultyName;
	}

	public void setFacultyName(String facultyName) {
		this.facultyName = facultyName;
	}
	
}
