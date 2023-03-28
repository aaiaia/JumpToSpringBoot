package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;//questionRepository/answerRepository 객체를 스프링이 자동으로 생성해주기 위함
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;

@SpringBootTest
public class Test009AddAnswer {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Test
	void testAddAnswer() {
		Optional<Question> oq = this.questionRepository.findById(2);
		if(oq.isPresent()) {
			assertTrue(oq.isPresent());
			Question q = oq.get();

			Answer a = new Answer();
			a.setContent("네 자동으로 생성됩니다.");
			a.setQuestion(q);
			a.setCreateDate(LocalDateTime.now());
			this.answerRepository.save(a);
		}
		else
		{
			assertFalse(oq.isPresent());
            System.out.println("아이디를 찾을 수 없습니다");
		}
	}
}
