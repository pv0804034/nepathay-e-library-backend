package com.nepathya.archive.system.security.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nepathya.archive.system.common.ResponseHandler;
import com.nepathya.archive.system.security.changePassword.ChangePasswordForm;
import com.nepathya.archive.system.security.login.AuthenticationResponseDto;
import com.nepathya.archive.system.security.role.Role;
import com.nepathya.archive.system.service.common.LocaleService;
import com.nepathya.archive.system.service.common.PasswordEncoderUtil;



@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoderUtil passwordEncoderUtil;
	
	@Autowired
	private LocaleService localeService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmailId(email);
		if (Objects.isNull(user)) {
			throw new UsernameNotFoundException(email);
		}
		return user;
	}

	public User findUserById(UUID userId) {
		return userRepository.findByUserId(userId);
	}
	
	public User findUserByPhone(String phoneNo) {
		return userRepository.findByPhone(phoneNo);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmailId(email);
	}

	public AuthenticationResponseDto generateAuthenticationResponse(User user) {
		return new AuthenticationResponseDto(user, null, null, null);
	}
	
	public ResponseEntity<?> deleteUserById(UUID userId) {
		User user = this.findUserById(userId);
		if (Objects.isNull(user)) {
			return ResponseHandler.response(HttpStatus.NOT_FOUND, false,
					localeService.getMessage("user.not.found"));
		} else {
			if(user.getAccountDeleted()) {
				return ResponseHandler.response(HttpStatus.BAD_REQUEST, false,
						localeService.getMessage("user.already.deleted"));
			}
		}
			
		user.setAccountDeleted(Boolean.TRUE);
		userRepository.save(user);
		return ResponseHandler.response(HttpStatus.OK, true, localeService.getMessage("user.deleted"));
	}

	public boolean changePassword(User user, @Valid ChangePasswordForm passwordForm) {
		if (passwordEncoderUtil.matches(passwordForm.getOldPassword(), user.getPassword())) {
			user.setPassword(passwordEncoderUtil.encode(passwordForm.getNewPassword()));
			userRepository.save(user);
			return true;
		}
		return false;
	}

	public ResponseEntity<?> saveUser(UserDTO userDTO, Role role) {
		User user = new User();
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmailId(userDTO.getEmail());
		user.setPassword(passwordEncoderUtil.encode(userDTO.getPassword()));
		user.setPhone(userDTO.getPhone());
		user.setRole(role);
		user.setIsActive(Boolean.FALSE);
		userRepository.save(user);
		return ResponseHandler.response(HttpStatus.OK, true, localeService.getMessage("user.created"));
	}
	
	public ResponseEntity<?> updateUser(UserDTO userDTO, Role role, User user) {
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setEmailId(userDTO.getEmail());
		user.setPhone(userDTO.getPhone());
		user.setRole(role);
		user.setPassword(userDTO.getPassword());
		userRepository.save(user);
		return ResponseHandler.response(HttpStatus.OK, true, localeService.getMessage("user.updated"));
	}

	public List<UserResponseDto> getAllUsers() {
		return userRepository.findAllUsers().stream().map(user -> new UserResponseDto(user)).collect(Collectors.toList());
	}
	
	public ResponseEntity<?> updateVisibility(User user){
		user.setIsActive(!user.getIsActive());
		userRepository.save(user);
		return ResponseHandler.response(HttpStatus.OK, true, localeService.getMessage("user.updated"));
	}
	
	public ResponseEntity<?> findByRole(Role role){
		List<User> users = userRepository.findByRole(role);
		if(users.size() == 0) {
			return ResponseHandler.response(HttpStatus.BAD_GATEWAY, false, localeService.getMessage("user.not.found"));
		}
		List<UserResponseDto> userResponseDto = new ArrayList<UserResponseDto>();
		for(User u: users) {
			UserResponseDto uu = new UserResponseDto(u);
			userResponseDto.add(uu);
		}
		return ResponseHandler.response(HttpStatus.OK, true, userResponseDto);
	}
}
