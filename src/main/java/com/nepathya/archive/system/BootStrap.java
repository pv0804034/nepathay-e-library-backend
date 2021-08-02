package com.nepathya.archive.system;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.nepathya.archive.system.common.enums.UserRoleName;
import com.nepathya.archive.system.security.role.Role;
import com.nepathya.archive.system.security.role.UserRoleRepository;
import com.nepathya.archive.system.security.user.User;
import com.nepathya.archive.system.security.user.UserRepository;
import com.nepathya.archive.system.service.common.PasswordEncoderUtil;

@Component
public class BootStrap implements ApplicationListener<ContextRefreshedEvent> {

	@Value("${superadmin.emailId}")
	private String adminEmailId;
	@Value("${superadmin.password}")
	private String adminPass;

	@Autowired
	private PasswordEncoderUtil passwordEncoderUtil;

	@Autowired
	private UserRoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (NumberUtils.INTEGER_ZERO.equals(roleRepository.findAny())) {
			createRole();
		} else {

		}
		if (NumberUtils.INTEGER_ZERO.equals(userRepository.findAny())) {
			saveAdminOnStartUp();
		} else {

		}
		
	}

	public void createRole() {
		try {
			Role role1 = new Role(UserRoleName.ROLE_EXECUTIVE_SUPER_ADMIN.getName(), "Executive Super Admin");
			Role role2 = new Role(UserRoleName.ROLE_SUPER_ADMIN.getName(), "Super Admin");
			Role role3 = new Role(UserRoleName.ROLE_ADMIN.getName(), "Admin");
			Role role4 = new Role(UserRoleName.ROLE_USER.getName(), "User");
			List<Role> rolesList = Arrays.asList(role1, role2, role3, role4);
			roleRepository.saveAll(rolesList);
		} catch (Exception e) {

		}
	}

	public void saveAdminOnStartUp() {
		try {
			User superAdmin = new User();
			superAdmin.setFirstName("admin");
			superAdmin.setLastName("lastname");
			superAdmin.setEmailId(adminEmailId);
			superAdmin.setPassword(passwordEncoderUtil.encode(adminPass));
			superAdmin.setPhone("9866186418");
			superAdmin.setIsActive(Boolean.TRUE);
			Role role = roleRepository.findByRole(UserRoleName.ROLE_EXECUTIVE_SUPER_ADMIN.getName());
			superAdmin.setRole(role);
			userRepository.save(superAdmin);
		} catch (Exception e) {

			System.out.println(e.getMessage());
		}
	}
}