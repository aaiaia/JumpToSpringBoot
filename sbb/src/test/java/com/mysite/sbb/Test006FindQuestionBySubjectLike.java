package com.mysite.sbb;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;//questionRepository/answerRepository 객체를 스프링이 자동으로 생성해주기 위함
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;

@SpringBootTest
class Test006FindQuestionBySubjectLike {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	void testFindQuestionBySubjectLike () {
		List<String> targetTextList = new ArrayList<String>(); 
		targetTextList.add("sbb");
		targetTextList.add("sbb%");
		targetTextList.add("%sbb");
		targetTextList.add("%sbb%");
		
		for(String targetText : targetTextList) {
			System.out.println("<<<<<<<<<<==========>>>>>>>>>>");
			System.out.println("target text is \'" + targetText + "\'");
			List<Question> questionList = this.questionRepository.findBySubjectLike(targetText);
			System.out.println("found test number: " + questionList.size());
				for(int i = 0; i < questionList.size(); i++) {
					Question q = questionList.get(i);
					System.out.println("id: " + q.getId());
					System.out.println("subject: " + q.getSubject());
					System.out.println("context: " + q.getContent());
					System.out.println("date: " + q.getCreateDate());
				}
		}
	}
}