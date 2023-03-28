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
import org.springframework.data.domain.Page;

/* To Check Validation */
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;

import com.mysite.sbb.answer.AnswerForm;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionContoller {

	private final QuestionService questionService;

	/*
	 * previous function
	@GetMapping("/list")
	//@ResponseBody
	public String list(Model model) {
		List<Question> questionList = this.questionService.getList();
		model.addAttribute("questionList", questionList);
		return "question_list";
	}
	 */
	@GetMapping("/list")
	public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
		Page<Question> paging = this.questionService.getList(page);
		model.addAttribute("paging", paging);
		return "question_list";
	}

	@GetMapping(value = "/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
		Question q = questionService.getQuestion(id);
		model.addAttribute("question", q);
		return "question_detail";
	}

	/* for returning question_form.html to browser when QuestionForm has error */
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		System.out.println("called: " + new Object(){}.getClass().getEnclosingMethod().getName() + ", in " + this.getClass().getName());
		return "question_form";
	}
	/* for create question */
	@PostMapping("/create")
	/*
	 * Not checking validation
	public String questionCreate(@RequestParam String subject, @RequestParam  String content) {
	*/
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
		System.out.println("called: " + new Object(){}.getClass().getEnclosingMethod().getName() + ", in " + this.getClass().getName());
		if(!bindingResult.hasErrors()) {
			// TODO 질문을 저장한다.
			this.questionService.create(questionForm.getSubject(), questionForm.getContent());
			return "redirect:/question/list";	// 질문 저장후 질문목록으로 이동
		} else {
			return "question_form";
		}
	}
}
