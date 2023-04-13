package com.mysite.sbb.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/* for common excepion in this projects*/
import com.mysite.sbb.DataNotFoundException;

@RequiredArgsConstructor
@Service
public class SiteUserService {
	private final SiteUserRepository siteUserRepository;
	private final PasswordEncoder passwordEncoder;

	public SiteUser create(String username, String password, String email) {
		SiteUser user = new SiteUser();
		user.setUsername(username);
		user.setPassword(passwordEncoder.encode(password));
		user.setEmail(email);

		siteUserRepository.save(user);
		return user;
	}

	public SiteUser getUser(String username) {
		Optional<SiteUser> siteUser = this.siteUserRepository.findByUsername(username);
		if(siteUser.isPresent()) {
			return siteUser.get();
		} else {
			throw new DataNotFoundException("siteuser not found");
		}
	}
}
