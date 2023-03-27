package com.mysite.sbb.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {

	private final QuestionService questionService;
	private final AnswerService answerService;

	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id, @RequestParam String content) {
		System.out.println("called: " + new Object(){}.getClass().getEnclosingMethod().getName() + ", in " + this.getClass().getName());
		Question question = this.questionService.getQuestion(id);
		// TODO: 답변을 저장한다.
		answerService.create(question, content);
        /* Return to Browser, createAnswer() called only(fixed) @url: /question/detail/{id} */
		return String.format("redirect:/question/detail/%s", id);
	}

}
