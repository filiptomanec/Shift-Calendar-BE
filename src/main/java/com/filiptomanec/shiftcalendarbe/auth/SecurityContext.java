package com.filiptomanec.shiftcalendarbe.auth;

import com.filiptomanec.shiftcalendarbe.user.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityContext {
	public boolean isAuthenticated() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && authentication.isAuthenticated();
	}

	public User getUser() {
		if (!isAuthenticated()) {
			throw new RuntimeException();
		}
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
