package com.nepathya.archive.system.security.role;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nepathya.archive.system.common.ResponseHandler;
import com.nepathya.archive.system.service.common.LocaleService;


@Service
public class RoleService {

	@Autowired
	private LocaleService localeService;
	
	@Autowired
	private UserRoleRepository userRoleRepository;

	public List<RoleResponseDto> getAllRoles() {
		return userRoleRepository.findAll().stream().map(role -> new RoleResponseDto(role))
				.collect(Collectors.toList());

	}
	
	public Role findByRole(String role) {
		return userRoleRepository.findByRole(role);
	}

	public ResponseEntity<?> getRoleByRoleId(UUID roleId) {
		Role role = userRoleRepository.findByRoleId(roleId);
		if(Objects.isNull(role)) {
			return ResponseHandler.response(HttpStatus.NOT_FOUND, false,
					localeService.messageResponse("role.not.found"));
		}
		return ResponseHandler.response(HttpStatus.OK, true,
				role);
	}

	public ResponseEntity<?> save(RoleRequestDto roleRequestDto) {
		Role checkRole = userRoleRepository.findByRole(roleRequestDto.getRole());
		if (Objects.nonNull(checkRole))
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,
					localeService.messageResponse("role.already.present"));
		Role role = new Role(roleRequestDto.getRole(), roleRequestDto.getDescription());
		userRoleRepository.save(role);
		return ResponseHandler.response(HttpStatus.OK, true,
				localeService.messageResponse("role.created"));
	}
	
	public ResponseEntity<?> update(RoleRequestDto roleRequestDto, UUID roleId) {
		Role role = userRoleRepository.findByRoleId(roleId);
		if(Objects.isNull(role)) {
			return ResponseHandler.response(HttpStatus.NOT_FOUND, false,
					localeService.messageResponse("role.not.found"));
		}
		
		if(NumberUtils.INTEGER_ONE.equals(userRoleRepository.checkExistForUpdate(roleId, roleRequestDto.getRole())))
			return ResponseHandler.response(HttpStatus.CONFLICT, false, localeService.messageResponse("role.already.present"));
		
		role.setRole(roleRequestDto.getRole());
		role.setDescription(roleRequestDto.getDescription());
		userRoleRepository.save(role);
		return ResponseHandler.response(HttpStatus.OK, true,
				localeService.messageResponse("role.updated"));
	}
	
	public ResponseEntity<?> delete(UUID roleId){
		Role role = userRoleRepository.findByRoleId(roleId);
		if(Objects.isNull(role)) {
			return ResponseHandler.response(HttpStatus.NOT_FOUND, false,
					localeService.messageResponse("role.not.found"));
		}
		userRoleRepository.delete(role);
		return ResponseHandler.response(HttpStatus.OK, true,
				localeService.messageResponse("role.deleted"));
	}
}
