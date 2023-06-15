package com.cantelli.invisolar.controller;

import com.cantelli.invisolar.domain.User;
import com.cantelli.invisolar.domain.security.Role;
import com.cantelli.invisolar.domain.security.UserRole;
import com.cantelli.invisolar.service.UserService;
import com.cantelli.invisolar.service.impl.UserSecurityService;
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
        user.setPassword(password);

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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(
            @ModelAttribute("username") String username,
            @ModelAttribute("password") String password,
            Model model) {

        model.addAttribute("username", username);
        model.addAttribute("password", password);

        User user = userService.findByUsername(username);

        if (user == null) {
            model.addAttribute("loginError", true);
            return "myAccount";
        }

        if (!(password.equals(user.getPassword()))) {
            model.addAttribute("loginError", true);
            return "myAccount";
        }

        model.addAttribute("user", user);
        model.addAttribute("userLogged", true);

        return "index";
    }

    @RequestMapping(value="/updateUsername", method = RequestMethod.POST)
    public String updateUsername(@ModelAttribute("username") String username,
                                 @ModelAttribute("email") String userEmail,
                                 Model model) throws Exception{

        model.addAttribute("username", username);
        model.addAttribute("email", userEmail);

        User user = userService.findByEmail(userEmail);

        if ((userService.findByUsername(username) != null)&&(!(username.equals(user.getUsername())))) {
            model.addAttribute("usernameExists", true);
        }else{
            user.setUsername(username);
            model.addAttribute("updatedUserInfo", true);
        }

        model.addAttribute("user", user);

        return"myProfile";
    }

    @RequestMapping(value="/editUserInfo", method = RequestMethod.POST)
    public String updateUserInfo(@ModelAttribute("firstName") String firstName,
                                 @ModelAttribute("lastName") String lastName,
                                 @ModelAttribute("username") String username,
                                 @ModelAttribute("email") String userEmail,
                                 @ModelAttribute("password") String userPassword,
                                 @ModelAttribute("phone") String userPhone,
                                 Model model) throws Exception{

        User user = userService.findByUsername(username);

        user.setPhone(userPhone);
        user.setLastName(lastName);
        user.setFirstName(firstName);

        if ((userService.findByEmail(userEmail) != null)&&(!(userEmail.equals(user.getEmail())))) {
            model.addAttribute("emailExists", true);
        }else{
            user.setEmail(userEmail);
        }

        if(userPassword.length()<6){
            model.addAttribute("passwordTooShort", true);
        }else{
            user.setPassword(userPassword);
        }

        model.addAttribute("updatedUserInfo", true);

        model.addAttribute("user", user);

        return"myProfile";
    }

    @RequestMapping("/forgotPassword")
    public String forgetPassword(
            HttpServletRequest request,
            @ModelAttribute("email") String email,
            Model model
    ) {

        model.addAttribute("classActiveForgetPassword", true);

        User user = userService.findByEmail(email);

        if (user == null) {
            model.addAttribute("emailNotExist", true);
            return "myAccount";
        }

        return "myAccount";
    }

    @RequestMapping("/profiling")
    public String profiling(){
        return"profiling";
    }

    @RequestMapping(value = "/startingpowerForm", method = RequestMethod.POST)
    public String startingpowerForm(
            @RequestAttribute("firstName") String firstName,
            Model model){
        model.addAttribute("startingPower", true);
        return"profiling";
    }

    @RequestMapping(value = "/profilingForm", method = RequestMethod.POST)
    public String profilingForm(
            @RequestAttribute("boiler") Boolean boiler,
            @RequestAttribute("car") Boolean car,
            @RequestAttribute("km") long km,
            @RequestAttribute("badOrientation") Boolean badOrientation,
            Model model){

        return"profiling";
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