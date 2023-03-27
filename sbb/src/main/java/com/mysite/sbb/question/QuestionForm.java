package com.mysite.sbb.question;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class QuestionForm {
	@NotEmpty(message="제목을 필수항목입니다.(최대 200자)")
	@Size(max=200)
	private String subject;

	@NotEmpty(message="내용은 필수할목입니다.")
	private String Content;
}
