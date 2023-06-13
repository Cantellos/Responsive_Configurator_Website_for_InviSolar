package com.cantelli.invisolar.utility;

import com.cantelli.invisolar.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.*;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MailConstructor {
	@Autowired
	private Environment env;

	public SimpleMailMessage constructResetTokenEmail(
			String contextPath, Locale locale, String token, User user, String password
	) {

		String url = contextPath + "/signin?token="+token;
		String message = "\nPlease click on this link to verify your email and edit your personal information. Your password is: \n"+password;
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject("InviSolar - New User");
		email.setText(url+message);
		email.setFrom("andrea.cantelli@edu.unife.it");
		return email;

	}
}
