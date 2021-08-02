package com.nepathya.archive.system.security.role;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRoleRepository extends JpaRepository<Role, UUID> {
	
	Role findByRole(String roleUser);

	@Query(value = "SELECT EXISTS (SELECT 1 FROM role)", nativeQuery = true)
	Integer findAny();

	Role findByRoleId(UUID roleId);
	
	
	@Query(value = "SELECT EXISTS (SELECT 1 FROM role WHERE role_id <> :roleId AND role=:roleName);", nativeQuery = true)
	Integer checkExistForUpdate(@Param("roleId") UUID roleId, @Param("roleName") String roleName);
}
