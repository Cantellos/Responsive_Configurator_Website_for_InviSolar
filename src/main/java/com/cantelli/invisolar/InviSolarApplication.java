package com.cantelli.invisolar;

import com.cantelli.invisolar.domain.User;
import com.cantelli.invisolar.domain.security.Role;
import com.cantelli.invisolar.domain.security.UserRole;
import com.cantelli.invisolar.service.UserService;
import com.cantelli.invisolar.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class InviSolarApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(InviSolarApplication.class, args);
    }

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
        User user1 = new User();
        user1.setFirstName("Andrea");
        user1.setLastName("Cantelli");
        user1.setPhone("3458676089");
        user1.setUsername("a");
        user1.setPassword(SecurityUtility.passwordEncoder().encode("p"));
        user1.setEmail("andrea.cantelli@edu.unife.it");
        Set<UserRole> userRoles = new HashSet<>();
        Role role1= new Role();
        role1.setRoleId(1);
        role1.setName("ROLE_USER");
        userRoles.add(new UserRole(user1, role1));

        userService.createUser(user1, userRoles);
    }

}
