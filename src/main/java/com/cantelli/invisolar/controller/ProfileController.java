package com.cantelli.invisolar.controller;

import com.cantelli.invisolar.domain.User;
import com.cantelli.invisolar.service.UserService;
import com.cantelli.invisolar.service.impl.UserSecurityService;
import com.cantelli.invisolar.utility.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSecurityService userSecurityService;


    @RequestMapping("/myProfile")
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

        user.setPhone(userPhone);
        user.setLastName(lastName);
        user.setFirstName(firstName);

        if ((userService.findByEmail(userEmail) != null)&&!user.getEmail().equals(userEmail)) {
            model.addAttribute("emailExists", true);
        }else{
            user.setEmail(userEmail);
        }

        if(!currentPassword.isEmpty()&&!newPassword.isEmpty()){
            BCryptPasswordEncoder passwordEncoder = SecurityUtility.passwordEncoder();
            String dbPassword = user.getPassword();

            if(!passwordEncoder.matches(currentPassword, dbPassword)){
                model.addAttribute("wrongPassword", true);
            } else if(newPassword.length()<6){
                model.addAttribute("passwordTooShort", true);
            } else{
                user.setPassword(passwordEncoder.encode(newPassword));
            }
        }

        userService.save(user);

        model.addAttribute("updatedUserInfo", true);

        model.addAttribute("user", user);

        return"myProfile";
    }

}
