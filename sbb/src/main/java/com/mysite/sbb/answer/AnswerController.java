package com.mysite.sbb.answer;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;

/* To Check Validation */
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

/* To Get user(spring security) */
import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.server.ResponseStatusException;

import com.mysite.sbb.question.Question;
//import com.mysite.sbb.question.QuestionForm;
import com.mysite.sbb.question.QuestionService;

/* for SiteUser(author) */
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.SiteUserService;

import com.mysite.sbb.DataNotFoundException;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

	private final QuestionService questionService;
	private final AnswerService answerService;
	private final SiteUserService siteUserService;

	@PostMapping("/create/{id}")
	@PreAuthorize("isAuthenticated")
	/*
	 * previous method declare
	public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam String content) {
	*/
	public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal) {
		//System.out.println("called: " + new Object(){}.getClass().getEnclosingMethod().getName() + ", in " + this.getClass().getName());

		Question question = this.questionService.getQuestion(id);
		SiteUser siteUser = this.siteUserService.getUser(principal.getName());
		if(!bindingResult.hasErrors()) {
			// TODO: 답변을 저장한다.
			answerService.create(question, answerForm.getContent(), siteUser);
			/* Return to Browser, createAnswer() called only(fixed) @url: /question/detail/{id} */
			return String.format("redirect:/question/detail/%s", id);
		} else {
			model.addAttribute("question", question);
			return "question_detail";
		}
	}

	@GetMapping("/modify/{id}")
	@PreAuthorize("isAuthenticated")
	public String modifyAnswer(AnswerForm answerForm, @PathVariable("id") Integer id, Principal principal) {
		Answer answer = this.answerService.getAnswer(id);
		if(!answer.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		answerForm.setContent(answer.getContent());
		return "answer_form";
	}

	@PostMapping("/modify/{id}")
	@PreAuthorize("isAuthenticated")
	public String modifyAnswer(@Valid AnswerForm answerForm, BindingResult bindingResult, Principal principal,
			@PathVariable("id") Integer id) {
		if(bindingResult.hasErrors()) {
			return "answer_form";
		}
		Answer answer = this.answerService.getAnswer(id);
		if(!answer.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		this.answerService.modify(answer, answerForm.getContent());
		return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
	}
}
