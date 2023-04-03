package com.mysite.sbb.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class SiteUserCreateForm {
	@Size(min = 3, max = 25)
	@NotEmpty(message = "사용자 ID는 필수항목입니다.")
	private String username;

	@NotEmpty(message = "비밀번호는 필수항목입니다.")
	private String password1;

	@NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
	private String password2;

	@NotEmpty(message = "이메일은 필수항목입니다.")
	@Email
	private String email;
}
