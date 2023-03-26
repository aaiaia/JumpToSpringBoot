package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;//questionRepository/answerRepository 객체를 스프링이 자동으로 생성해주기 위함
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;

@SpringBootTest
class Test007ModifyContent {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	void testModifyContent() {
		int targetId = 1;
		Optional<Question> oq = this.questionRepository.findById(targetId);
		if(oq.isPresent()) {
			assertTrue(oq.isPresent());
			Question q = oq.get();
			System.out.println("Previous Subject: " + q.getSubject());
			q.setSubject("수정된 제목 " + LocalDateTime.now());
			this.questionRepository.save(q);
			System.out.println("Present Subject: " + q.getSubject());
		}
		else {
			assertFalse(oq.isPresent());
			System.out.println("Not found question.Id: " + targetId);
		}
	}
}