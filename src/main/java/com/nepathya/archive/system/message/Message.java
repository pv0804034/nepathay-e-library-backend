package com.nepathya.archive.system.message;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "tbl_message")
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_id", updatable = false, nullable = false, length = 16)
	private Integer messageId;
	
	@Column(name="full_name",nullable=false)
	private String fullName;
	
	@Column(name="email",nullable=false)
	private String email;
	
	@Column(name="message",columnDefinition="TEXT",nullable=false)
	private String message;
	
	public Message() {}
	
	public Message(String fullName, String email, String message) {
		this.fullName = fullName;
		this.email = email;
		this.message = message;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
