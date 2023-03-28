package com.mysite.sbb.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;

/* To Check Validation */
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import com.mysite.sbb.question.Question;
//import com.mysite.sbb.question.QuestionForm;
import com.mysite.sbb.question.QuestionService;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

	private final QuestionService questionService;
	private final AnswerService answerService;

	@PostMapping("/create/{id}")
	/*
	 * previous method declare
	public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam String content) {
	*/
	public String createAnswer(Model model, @PathVariable("id") Integer id, @Valid AnswerForm answerForm, BindingResult bindingResult) {
		System.out.println("called: " + new Object(){}.getClass().getEnclosingMethod().getName() + ", in " + this.getClass().getName());

		Question question = this.questionService.getQuestion(id);
		if(!bindingResult.hasErrors()) {
			// TODO: 답변을 저장한다.
			answerService.create(question, answerForm.getContent());
			/* Return to Browser, createAnswer() called only(fixed) @url: /question/detail/{id} */
			return String.format("redirect:/question/detail/%s", id);
		} else {
			model.addAttribute("question", question);
			return "question_detail";
		}
	}

}
