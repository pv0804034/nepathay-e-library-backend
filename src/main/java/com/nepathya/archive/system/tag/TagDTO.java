package com.nepathya.archive.system.tag;

public class TagDTO {
	
	private String tagName;
	
	public TagDTO() {}
	
	public TagDTO(String tagName) {
		this.tagName = tagName;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
}
