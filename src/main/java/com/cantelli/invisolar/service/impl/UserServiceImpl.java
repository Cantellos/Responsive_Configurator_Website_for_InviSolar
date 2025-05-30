package com.cantelli.invisolar.service.impl;

import com.cantelli.invisolar.domain.User;
import com.cantelli.invisolar.domain.security.UserRole;
import com.cantelli.invisolar.repository.RoleRepository;
import com.cantelli.invisolar.repository.UserRepository;
import com.cantelli.invisolar.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail (String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User createUser(User user, Set<UserRole> userRoles){
		User localUser = userRepository.findByUsername(user.getUsername());

		if(localUser != null) {
			LOG.info("user {} already exists. Nothing will be done.", user.getUsername());
		} else {
			for (UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}

			user.getUserRoles().addAll(userRoles);

			localUser = userRepository.save(user);
		}

		return localUser;
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

}

