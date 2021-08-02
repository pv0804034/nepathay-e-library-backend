package com.nepathya.archive.system.security.user;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nepathya.archive.system.security.role.Role;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	User findByEmailId(String email);
	
	User findByUserId(UUID userId);

	User findByEmailIdOrPhone(String email, String phone);

	User findByPhone(String value);
	
	List<User> findByRole(Role role);

	@Query(value = "SELECT EXISTS (SELECT 1 FROM users)", nativeQuery = true)
	Integer findAny();
	
	@Query(value = "SELECT * From users WHERE account_deleted = 0", nativeQuery = true)
	List<User> findAllUsers();
}
