package com.mysite.sbb.question;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;
	
	public List<Question> getList(){
		System.out.println("called getList(), in " + this.getClass().getName());
		return this.questionRepository.findAll();
	}
}
