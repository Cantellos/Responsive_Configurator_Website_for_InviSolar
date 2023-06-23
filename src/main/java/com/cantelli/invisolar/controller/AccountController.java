package com.cantelli.invisolar.controller;

import com.cantelli.invisolar.domain.User;
import com.cantelli.invisolar.service.UserService;
import com.cantelli.invisolar.service.impl.UserSecurityService;
import com.cantelli.invisolar.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService userSecurityService;

    @RequestMapping(value = "/myProfile")
    public String myProfile(Model model, Principal principal){
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return"myProfile";
    }

    @RequestMapping(value="/editUserInfo", method = RequestMethod.POST)
    public String updateUserInfo(
            Principal principal,
            @ModelAttribute("firstName") String firstName,
            @ModelAttribute("lastName") String lastName,
            @ModelAttribute("username") String username,
            @ModelAttribute("email") String userEmail,
            @ModelAttribute("newPassword") String newPassword,
            @ModelAttribute("currentPassword") String currentPassword,
            @ModelAttribute("phone") String userPhone,
            Model model) throws Exception{

        User user = userService.findByUsername(principal.getName());

        model.addAttribute("updatedUserInfo", false);

        if((firstName!=null)^(user.getFirstName()!=null)){
            user.setFirstName(firstName);
            model.addAttribute("updatedUserInfo", true);
        }else if((firstName!=null)&&(user.getFirstName()!=null)){
            if(!user.getFirstName().equals(firstName)) {
                model.addAttribute("updatedUserInfo", true);
                user.setFirstName(firstName);
            }
        }

        if((lastName!=null)^(user.getLastName()!=null)){
            model.addAttribute("updatedUserInfo", true);
            user.setLastName(lastName);
        } else if((lastName!=null)&&(user.getLastName()!=null)){
            if (!user.getLastName().equals(lastName)) {
                model.addAttribute("updatedUserInfo", true);
                user.setLastName(lastName);
            }
        }

        if((userPhone!=null)^(user.getPhone()!=null)){
            model.addAttribute("updatedUserInfo", true);
            user.setPhone(userPhone);
        } else if((userPhone!=null)&&(user.getPhone()!=null)) {
            if (!user.getPhone().equals(userPhone)) {
                model.addAttribute("updatedUserInfo", true);
                user.setPhone(userPhone);
            }
        }

        if(!(username.isEmpty())&&(user.getUsername()!=null)){
            if (userService.findByUsername(username) == null) {
                if(!(username.equals(user.getUsername()))) {
                    model.addAttribute("updatedUserInfo", true);
                    user.setUsername(username);
                }else model.addAttribute("usernameExists", true);
            }else {
                user.setUsername(username);
                model.addAttribute("usernameExists", true);
            }
        } else model.addAttribute("usernameNotNull", true);


        if(!(userEmail.isEmpty())&&(user.getEmail()!=null)){
            if ((userService.findByEmail(userEmail) == null)) {
                if(!(userEmail.equals(user.getEmail()))) {
                    model.addAttribute("updatedUserInfo", true);
                    user.setEmail(userEmail);
                } else model.addAttribute("emailExists", true);
            }else model.addAttribute("emailExists", true);
        } else model.addAttribute("emailNotNull", true);


        if(!currentPassword.isEmpty()&&!newPassword.isEmpty()){
            BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
            String dbPassword = user.getPassword();

            if(!passwordEncoder.matches(currentPassword, dbPassword)){
                model.addAttribute("wrongPassword", true);
            } else if(newPassword.length()<4){
                model.addAttribute("passwordTooShort", true);
            } else{
                user.setPassword(passwordEncoder.encode(newPassword));
                model.addAttribute("updatedUserInfo", true);
            }
        }

        user = userService.save(user);

        UserDetails userDetails = userSecurityService.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(),
                userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        model.addAttribute("user", user);

        return"myProfile";
    }

}
