package com.mysite.sbb.answer;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;

@Getter
@Setter
@Entity
public class Answer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(columnDefinition = "Text")
	private String content;

	private LocalDateTime createDate;
	private LocalDateTime modifiedDate;

	@ManyToOne
	private Question question;

	@ManyToOne
	SiteUser author;
}
