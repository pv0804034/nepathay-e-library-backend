package com.nepathya.archive.system.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nepathya.archive.system.constant.UrlConstant;

import io.swagger.annotations.Api;

@Api
@RequestMapping(UrlConstant.MESSAGE)
@RestController
public class MessageController {
	
	@Autowired
	MessageService messageService;
	
	@PostMapping
	public ResponseEntity<?> saveMessage(@RequestBody MessageDTO messageDTO, BindingResult bindingResult){
		return messageService.saveMessage(messageDTO);
	}
	
	@GetMapping
	public ResponseEntity<?> getMessage(@RequestHeader String authorization){
		return messageService.getMessage();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteMessage(@RequestHeader String authorization, @PathVariable("id") Integer id){
		return messageService.deleteMessage(id);
	}
			
}
