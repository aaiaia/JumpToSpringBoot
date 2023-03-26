package com.mysite.sbb;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.question.Question;
//import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;

@SpringBootTest
public class Test010FindAnswerById {

	@Autowired
	private AnswerRepository answerRepository;
	
	@Test
	void testFindAnswer() {
		Optional<Answer> oa = this.answerRepository.findById(1);
		if(oa.isPresent()) {
			assertTrue(oa.isPresent());
			Answer a = oa.get();
			Question q = a.getQuestion();

			System.out.println(q.getId() +  ":" + q.getCreateDate());
			System.out.println("subject: " + q.getSubject());
			System.out.println("content: " + q.getContent());

			System.out.println(a.getId() +  ":" + a.getCreateDate());
			System.out.println("content: " + a.getContent());
		}
		else {
			assertFalse(oa.isPresent());
		}
	}
}
