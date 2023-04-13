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
import org.springframework.http.HttpStatus;

/* To Check Validation */
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;

/* To Get user(spring security) */
import java.security.Principal;
import org.springframework.security.access.prepost.PreAuthorize;

import com.mysite.sbb.answer.AnswerForm;

/* for SiteUser(author) */
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.SiteUserService;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionContoller {

	private final QuestionService questionService;
	private final SiteUserService siteUserService;

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
	@PreAuthorize("isAuthenticated")
	public String questionCreate(QuestionForm questionForm) {
		System.out.println("called: " + new Object(){}.getClass().getEnclosingMethod().getName() + ", in " + this.getClass().getName());
		return "question_form";
	}
	/* for create question */
	@PostMapping("/create")
	@PreAuthorize("isAuthenticated")
	/*
	 * Not checking validation
	public String questionCreate(@RequestParam String subject, @RequestParam  String content) {
	*/
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal) {
		//System.out.println("called: " + new Object(){}.getClass().getEnclosingMethod().getName() + ", in " + this.getClass().getName());

		SiteUser siteUser = siteUserService.getUser(principal.getName());
		if(!bindingResult.hasErrors()) {
			// TODO 질문을 저장한다.
			this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
			return "redirect:/question/list";	// 질문 저장후 질문목록으로 이동
		} else {
			return "question_form";
		}
	}

	@GetMapping("/modify/{id}")
	@PreAuthorize("isAuthenticated")
	public String questionModify(QuestionForm questionForm, @PathVariable("id") Integer id, Principal principal) {
		Question question = this.questionService.getQuestion(id);
		if(!question.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		else {
			// updates subject and content to question_form.html(web page)
			questionForm.setSubject(question.getSubject());
			questionForm.setContent(question.getContent());
			return "question_form";
		}
	}

	@PostMapping("/modify/{id}")
	@PreAuthorize("isAuthenticated")
	public String questionModify(@Valid QuestionForm questionForm, BindingResult bindingResult, Principal principal,
			@PathVariable("id") Integer id){
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		Question question = this.questionService.getQuestion(id);
		if(!question.getAuthor().getUsername().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
		}
		else {
			// updates question
			this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
			return String.format("redirect:/question/detail/%s", id);
		}
	}
}
