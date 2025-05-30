package com.cantelli.invisolar;

import com.cantelli.invisolar.domain.User;
import com.cantelli.invisolar.domain.security.Role;
import com.cantelli.invisolar.domain.security.UserRole;
import com.cantelli.invisolar.service.UserService;

import com.cantelli.invisolar.utility.SecurityUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class InviSolarApplication implements CommandLineRunner {

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(InviSolarApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setFirstName("John");
        user1.setLastName("Adams");
        user1.setUsername("j");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
        user1.setEmail("JAdams@gmail.com");
        Set<UserRole> userRoles = new HashSet<>();
        Role role1= new Role();
        role1.setRoleId(1);
        role1.setName("ROLE_USER");
        userRoles.add(new UserRole(user1, role1));
        userService.createUser(user1, userRoles);

        User user2 = new User();
        user2.setFirstName("Admin");
        user2.setLastName("Admin");
        user2.setUsername("admin");
        user2.setPassword(SecurityUtility.passwordEncoder().encode("p"));
        user2.setEmail("andrea.cantelli@edu.unife.it");
        Set<UserRole> userRolesAdmin = new HashSet<>();
        Role role2= new Role();
        role2.setRoleId(2);
        role2.setName("ROLE_ADMIN");
        userRolesAdmin.add(new UserRole(user2, role2));
        userService.createUser(user2, userRolesAdmin);
    }

}