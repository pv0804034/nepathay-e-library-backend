package com.nepathya.archive.system.service.fileupload;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.io.FilenameUtils;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "file_meta_data")
public class FileMetaData {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "file_id", updatable = false, nullable = false, length = 16)
	private UUID fileId;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "mime_type", nullable = false)
	private String mimeType;
	
	@Column(name = "extension", nullable = false)
	private String extension;
	
	@Column(name = "file_size", nullable = false)
	private Long fileSize;
	
	@Column(name = "created_date", nullable = false)
	@CreationTimestamp
	private LocalDateTime createdDate;

	@Column(name = "modified_date", nullable = false)
	@UpdateTimestamp
	private LocalDateTime modifiedDate;
	
	public FileMetaData() {
		// TODO Auto-generated constructor stub
	}
	
	public FileMetaData(MultipartFile file) {
		super();
		String originalName = file.getOriginalFilename();
		String extension = FilenameUtils.getExtension(originalName);
		this.extension = extension;
		this.name = String.valueOf(System.currentTimeMillis()) + "." + extension;
		this.mimeType = file.getContentType();
		this.fileSize = file.getSize();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Long getFileSize() {
		return fileSize;
	}

	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}

}
