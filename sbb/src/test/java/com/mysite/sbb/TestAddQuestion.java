package com.mysite.sbb;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;//questionRepository/answerRepository 객체를 스프링이 자동으로 생성해주기 위함
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestAddQuestion {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	void testJpaAddQuestion() {
		Question q1 = new Question();
		q1.setSubject("sbb가 무엇인가요?");
		q1.setContent("sbb가 알고 싶어요");
		q1.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q1);

		Question q2 = new Question();
		q2.setSubject("스프링부트 모델 질문입니다.");
		q2.setContent("id는 자동으로 생성되나요?");
		q2.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q2);

		Question q3 = new Question();
		q3.setSubject("sbb가 무엇인가요?");
		q3.setContent("진짜 그게 뭔가요? 1도 모르겠어요");
		q3.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(q3);
	}
}