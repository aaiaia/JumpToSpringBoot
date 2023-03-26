package com.mysite.sbb;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;//questionRepository/answerRepository 객체를 스프링이 자동으로 생성해주기 위함
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Test002FindAllQuestions {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	void testJpaInsertQuestion() {
		List<Question> questionAll = this.questionRepository.findAll();
		assertEquals(2, questionAll.size());
		
		for(int i = 0; i < questionAll.size(); i++) {
			Question q = questionAll.get(i);
			System.out.println("id: " + q.getId());
			System.out.println("subject: " + q.getSubject());
			System.out.println("context: " + q.getContent());
			System.out.println("date: " + q.getCreateDate());
		}
	}
}