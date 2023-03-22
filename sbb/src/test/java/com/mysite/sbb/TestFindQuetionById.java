package com.mysite.sbb;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;//questionRepository/answerRepository 객체를 스프링이 자동으로 생성해주기 위함
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestFindQuetionById {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	void testJpaFindQuestionById() {
		Optional<Question> questionOpt = this.questionRepository.findById(1);
		if(questionOpt.isPresent()) {
			Question q = questionOpt.get();
			assertEquals("sbb가 무엇인가요?", q.getSubject());

			System.out.println("id: " + q.getId());
			System.out.println("subject: " + q.getSubject());
			System.out.println("context: " + q.getContent());
			System.out.println("date: " + q.getCreateDate());
		}
		
	}
}