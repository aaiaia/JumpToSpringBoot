package com.mysite.sbb;

//import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;/* Set DB Transaction mode, DB session is not closed until methed is closed */

@SpringBootTest
public class Test011FindAnserListByQuestion {

	@Autowired
	private QuestionRepository questionRepository;

	@Transactional
	@Test
	void testFindAnswerLIst() {
		Optional<Question> oq = this.questionRepository.findById(2);
		if(oq.isPresent()) {
			assertTrue(oq.isPresent());
			Question q = oq.get();

			List<Answer> answerList = q.getAnswerList();

			System.out.println(q.getId() + ":" + q.getCreateDate());
			System.out.println("subject: " + q.getSubject());
			System.out.println("content: " + q.getContent());
			System.out.println("answer num: " + answerList.size());
		}
		else {
			assertFalse(oq.isPresent());
		}
	}
}
