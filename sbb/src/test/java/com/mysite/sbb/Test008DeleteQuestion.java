package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;//questionRepository/answerRepository 객체를 스프링이 자동으로 생성해주기 위함
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Test008DeleteQuestion {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	void testDeleteQuestion () {
		Optional<Question> oq = this.questionRepository.findById(1);
		if(oq.isPresent()) {
			assertTrue(oq.isPresent());
			Question q = oq.get();
			this.questionRepository.delete(q);
			System.out.println("Question is deleted: " + q);
		}
		else {
			System.out.println("Question is not exist!!!");
		}
		System.out.println("Number of Question: " + this.questionRepository.count());
	}
}