package com.mysite.sbb.answer;

import jakarta.validation.constraints.NotEmpty;

import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class AnswerForm {
	@NotEmpty(message="답변내용은 필수입니다.")
	private String content;
}
