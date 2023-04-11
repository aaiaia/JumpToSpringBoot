package com.mysite.sbb;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.question.QuestionService;

@SpringBootTest
public class Test012_3_08_AddTestData_300 {

	@Autowired
	private QuestionService questionService;

	@Test
	void testJpaAddTestDataForPaging() {
		for(int i = 0; i < 300; i++) {
			String subject = String.format("테스트 제목입니다:[%03d]", i);
			String content = String.format("내용무:[%03d]", i);
			//this.questionService.create(subject, content);	// before 3.08, not set author(class SiteUser)
			this.questionService.create(subject, content, null);
		}
	}
}
