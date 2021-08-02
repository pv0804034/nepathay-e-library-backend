package com.nepathya.archive.system.security.role;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nepathya.archive.system.common.ErrorCollection;
import com.nepathya.archive.system.common.ResponseHandler;
import com.nepathya.archive.system.constant.UrlConstant;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping(UrlConstant.ROLE)
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping
	public List<RoleResponseDto> getAllRoles(@RequestHeader String authorization) {
		return roleService.getAllRoles();
	}
	
	@GetMapping("/{roleId}")
	public ResponseEntity<?> getAllRoles(@RequestHeader String authorization,@PathVariable("roleId") UUID roleId) {
		return roleService.getRoleByRoleId(roleId);
	}

	@PostMapping
	public ResponseEntity<?> createRole(@RequestBody RoleRequestDto roleRequestDto, BindingResult bindingResult,
			@RequestHeader String authorization) {

		if (bindingResult.hasErrors()) {
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false, ErrorCollection.getErrorMap(bindingResult));
		}

		return roleService.save(roleRequestDto);
	}
	
	@PutMapping("/{roleId}")
	public ResponseEntity<?> updateRole(@PathVariable("roleId") UUID roleId, @RequestBody RoleRequestDto roleRequestDto, BindingResult bindingResult,
			@RequestHeader String authorization) {

		if (bindingResult.hasErrors()) {
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false, ErrorCollection.getErrorMap(bindingResult));
		}
		
		return roleService.update(roleRequestDto,roleId);
	}
	
	@DeleteMapping("/{roleId}")
	public ResponseEntity<?> deleteRole(@PathVariable("roleId") UUID roleId, @RequestHeader String authorization){
		
		return roleService.delete(roleId);
	}
	

}
