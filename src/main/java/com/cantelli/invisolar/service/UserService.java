package com.cantelli.invisolar.service;

import com.cantelli.invisolar.domain.User;
import com.cantelli.invisolar.domain.security.UserRole;

import java.util.Set;

public interface UserService {

	User findByUsername(String username);

	User findByEmail (String email);

	User createUser(User user, Set<UserRole> userRoles) throws Exception;

	User save(User user);
}
