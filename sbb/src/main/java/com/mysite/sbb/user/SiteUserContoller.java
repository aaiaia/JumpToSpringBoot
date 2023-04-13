package com.mysite.sbb.user;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.dao.DataIntegrityViolationException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class SiteUserContoller {

	private final SiteUserService siteUserService;

	@GetMapping("/signup")
	public String signup(SiteUserCreateForm siteUserCreateForm) {
		return "signup_form";
	}

	@PostMapping("/signup")
	public String signup(@Valid SiteUserCreateForm siteUserCreateForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "signup_form";
		}

		if(!siteUserCreateForm.getPassword1().equals(siteUserCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 암호가 일치하지 않습니다.");
			return "signup_form";
		}

		try {
			siteUserService.create(siteUserCreateForm.getUsername(), siteUserCreateForm.getPassword1(), siteUserCreateForm.getEmail());
		}catch(org.springframework.dao.DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupDuplicatedID", "이미 등록된 사용자입니다.");
			return "signup_form";
		}catch(Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());
			return "signup_form";
		}

		return "redirect:/";
	}

	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
}
