package com.cantelli.invisolar;

import com.cantelli.invisolar.domain.User;
import com.cantelli.invisolar.domain.security.Role;
import com.cantelli.invisolar.domain.security.UserRole;
import com.cantelli.invisolar.service.UserService;

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
public class InviSolarApplication {

    private static final Logger log = LoggerFactory.getLogger(InviSolarApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(InviSolarApplication.class, args);
    }

}