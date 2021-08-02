package com.nepathya.archive.system.message;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nepathya.archive.system.common.ResponseHandler;
import com.nepathya.archive.system.service.common.LocaleService;

@Service
public class MessageService {

	@Autowired
	MessageRepository messageRepository;
	
	@Autowired
	LocaleService localeService;
	
	public ResponseEntity<?> saveMessage(MessageDTO messageDTO){
		Message message = new Message(messageDTO.getFullName(),messageDTO.getEmail(),messageDTO.getMessage());
		messageRepository.save(message);
		System.out.println(messageDTO.getEmail());
		return ResponseHandler.response(HttpStatus.OK, true, localeService.getMessage("message.send"));
	}
	
	public ResponseEntity<?> getMessage(){
		return ResponseHandler.response(HttpStatus.OK, true, messageRepository.findAll());
	}
	
	public ResponseEntity<?> deleteMessage(Integer id){
		Message message = messageRepository.findByMessageId(id);
		if(Objects.isNull(message)) {
			return ResponseHandler.response(HttpStatus.BAD_GATEWAY, false, localeService.getMessage("message.not.found"));
		}
		
		messageRepository.delete(message);
		return ResponseHandler.response(HttpStatus.OK, true , "successfully deleted");
	}
}
