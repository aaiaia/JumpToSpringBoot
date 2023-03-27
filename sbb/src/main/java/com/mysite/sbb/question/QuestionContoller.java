package com.mysite.sbb.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;	// to getting values from args of method, ex anno.) METHOD_NAME(@RequestParam String subject, ...)
import org.springframework.web.bind.annotation.PathVariable;	// to use path variable, ex anno.) @GetMapping(value = "/detail/{id}")
//import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionContoller {

	private final QuestionService questionService;

	@GetMapping("/list")
	//@ResponseBody
	public String list(Model model) {
		List<Question> questionList = this.questionService.getList();
		model.addAttribute("questionList", questionList);
		return "question_list";
	}

	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Question q = questionService.getQuestion(id);
		model.addAttribute("question", q);
		return "question_detail";
	}

	@PostMapping("/create")
	public String questionCreate(@RequestParam String subject, @RequestParam  String content) {
		System.out.println("called: " + new Object(){}.getClass().getEnclosingMethod().getName() + ", in " + this.getClass().getName());
		// TODO 질문을 저장한다.
		return "redirect:/question/list";	// 질문 저장후 질문목록으로 이동
	}
}
