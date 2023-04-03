package com.mysite.sbb.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SiteUserService {
	private final SiteUserRepository siteUserRepository;

	public SiteUser create(String username, String password, String email) {
		BCryptPasswordEncoder passwdEncoder = new BCryptPasswordEncoder();
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setPassword(passwdEncoder.encode(password));
		user.setEmail(email);

		siteUserRepository.save(user);
		return user;
	}
}
