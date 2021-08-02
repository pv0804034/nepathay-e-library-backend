package com.nepathya.archive.system.message;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
	
	Message findByMessageId(Integer id);
}
