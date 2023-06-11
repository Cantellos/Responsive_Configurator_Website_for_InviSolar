package com.cantelli.invisolar.repository;

import com.cantelli.invisolar.domain.security.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByname(String name);
}
