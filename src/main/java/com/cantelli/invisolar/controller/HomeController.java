package com.cantelli.invisolar.controller;

import com.cantelli.invisolar.domain.User;
import com.cantelli.invisolar.domain.security.Role;
import com.cantelli.invisolar.domain.security.UserRole;
import com.cantelli.invisolar.service.UserService;
import com.cantelli.invisolar.service.impl.UserSecurityService;
import com.cantelli.invisolar.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService userSecurityService;

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/signin")
    public String newUser(Model model) {
        model.addAttribute("classActiveNewAccount", true);
        return "myAccount";
    }

    @RequestMapping(value="/signin", method = RequestMethod.POST)
    public String newUserPost(
            @ModelAttribute("email") String userEmail,
            @ModelAttribute("username") String username,
            @ModelAttribute("password") String password,
            Model model) throws Exception {

        model.addAttribute("classActiveNewAccount", true);
        model.addAttribute("email", userEmail);
        model.addAttribute("username", username);
        model.addAttribute("password", password);

        if (userService.findByUsername(username) != null) {
            model.addAttribute("usernameExists", true);
            return "myAccount";
        }

        if (userService.findByEmail(userEmail) != null) {
            model.addAttribute("emailExists", true);
            return "myAccount";
        }

        if(password.length()<6){
            model.addAttribute("passwordTooShort", true);
            return "myAccount";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(userEmail);

        String encryptedPassword = SecurityUtility.passwordEncoder().encode(password);
        user.setPassword(encryptedPassword);

        Role role = new Role();
        role.setRoleId(1);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles = new HashSet<>();
        userRoles.add(new UserRole(user, role));
        userService.createUser(user, userRoles);

        UserDetails userDetails = userSecurityService.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        model.addAttribute("user", user);

        model.addAttribute("firstTime", true);

        return "myProfile";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("classActiveLogin", true);
        return "myAccount";
    }

    @RequestMapping("/configurator")
    public String configurator(){
        return"configurator";
    }

    @RequestMapping("/booking")
    public String booking(){
        return"booking";
    }

    @RequestMapping("/faq")
    public String faq(){
        return"faq";
    }

    @RequestMapping("/about")
    public String about(){
        return"about";
    }

}