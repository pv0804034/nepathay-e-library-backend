package com.nepathya.archive.system.security.user;

import java.util.List;
import java.util.Objects;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nepathya.archive.system.common.ResponseHandler;
import com.nepathya.archive.system.constant.UrlConstant;
import com.nepathya.archive.system.security.role.Role;
import com.nepathya.archive.system.security.role.RoleService;
import com.nepathya.archive.system.security.role.UserRoleRepository;
import com.nepathya.archive.system.service.common.LocaleService;

import io.swagger.annotations.Api;

@Api
@RestController
@RequestMapping(UrlConstant.USER)
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private LocaleService localeService;
	
	@Autowired
	UserRoleRepository roleRepository;
	
	@GetMapping
	public List<UserResponseDto> getAllUsers(@RequestHeader String authorization) {
		return userService.getAllUsers();
	}

	@PostMapping
	public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO, BindingResult bindingResult) {
		System.out.println(userDTO.getFaculty() + "_" + userDTO.getYear().toString());
		Role role = roleRepository.findByRole(userDTO.getFaculty() + "_" + userDTO.getYear().toString());
		System.out.println(role.getRole());
		if (Objects.isNull(role))
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,
					localeService.getMessage("role.does.present"));

		userService.saveUser(userDTO, role);
		return ResponseHandler.response(HttpStatus.OK, true, localeService.getMessage("user.created.successfully"));
	}

	@GetMapping(UrlConstant.BY_ID)
	public UserResponseDto getUser(@PathVariable("id") UUID userId, @RequestHeader String authorization) {
		User user = userService.findUserById(userId);
		return new UserResponseDto(user);
	}
	
	@PutMapping(UrlConstant.BY_ID)
	public ResponseEntity<?> updateUser(@PathVariable("id") UUID userId, @RequestBody UserDTO userDTO, BindingResult bindingResult,
			@RequestHeader String authorization) {
		User user = userService.findUserById(userId);
		if (Objects.isNull(user))
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,
					localeService.getMessage("user.does.exist"));
		
		Role role = roleService.findByRole(userDTO.getFaculty() + "_" + userDTO.getYear().toString());
		if (Objects.isNull(role))
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,
					localeService.getMessage("role.does.present"));

		userService.updateUser(userDTO, role, user);
		return ResponseHandler.response(HttpStatus.OK, true, localeService.getMessage("user.updated.successfully"));
	}
	
	@DeleteMapping(UrlConstant.BY_ID)
	public ResponseEntity<?> deleteUser(@PathVariable("id") UUID userId, @RequestHeader String authorization) {
		return userService.deleteUserById(userId);
	}
	
	@PutMapping("/change")
	public ResponseEntity<?> changeActive(@RequestParam String id,
			@RequestHeader String authorization) {
		System.out.println(id);
		User user = userService.findUserById(UUID.fromString(id));
		if (Objects.isNull(user))
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,
					localeService.getMessage("user.does.exist"));
		return userService.updateVisibility(user);
	}
	
	@GetMapping("/{roleName}")
	public ResponseEntity<?> findByRoleName(@RequestParam String roleName,@RequestHeader String authorization){
		Role role = roleRepository.findByRole(roleName);
		if (Objects.isNull(role))
			return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,
					localeService.getMessage("role.does.exist"));
		
		return userService.findByRole(role);
	}
	
}
