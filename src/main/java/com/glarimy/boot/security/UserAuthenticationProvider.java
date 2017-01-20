package com.glarimy.boot.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component("authenticationProvider")
public class UserAuthenticationProvider extends DaoAuthenticationProvider {

	@Autowired
	UserDAO userDAO;

	@Autowired
	@Qualifier("userService")
	@Override
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		super.setUserDetailsService(userDetailsService);
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			Authentication auth = super.authenticate(authentication);
			userDAO.resetFailAttempts(authentication.getName());
			return auth;
		} catch (BadCredentialsException e) {
			userDAO.updateFailAttempts(authentication.getName());
			throw e;
		} catch (LockedException e) {
			String error = "";
			UserAttempts userAttempts = userDAO.getUserAttempts(authentication.getName());
			if (userAttempts != null) {
				Date lastAttempts = userAttempts.getLastModified();
				error = "User account is locked! <br><br>Username : " + authentication.getName()
						+ "<br>Last Attempts : " + lastAttempts;
			} else {
				error = e.getMessage();
			}
			throw new LockedException(error);
		}
	}
}