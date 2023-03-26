package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;//questionRepository/answerRepository 객체를 스프링이 자동으로 생성해주기 위함
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test009AddAnswer {

	@Autowired
	private AnswerRepository answerRepository;
	
	@Test
	void testAddAnswer() {
		Optional<Answer> oq = this.answerRepository.findById(2);
		if(oq.isPresent()) {
			assertTrue(oq.isPresent());
		}
		else
		{
			assertTrue(oq.isPresent());
			
		}
	}

}
