package com.mysite.sbb;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;//questionRepository/answerRepository 객체를 스프링이 자동으로 생성해주기 위함
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Test004FindQuestionBySubject {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	void testJpaFindQuestionBySubject() {
		List<Question> questionList = this.questionRepository.findBySubject("sbb가 무엇인가요?");
		for(int i = 0; i < questionList.size(); i++) {
			Question q = questionList.get(i);
			System.out.println("id: " + q.getId());
			System.out.println("subject: " + q.getSubject());
			System.out.println("context: " + q.getContent());
			System.out.println("date: " + q.getCreateDate());
		}
	}
}