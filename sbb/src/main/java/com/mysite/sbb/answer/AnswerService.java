package com.mysite.sbb.answer;

import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.mysite.sbb.question.Question;

@RequiredArgsConstructor
@Service
public class AnswerService {

	private final AnswerRepository answerRepository;

	public void create(Question question, String content) {
		/* Just Debugging */
		System.out.println("called: " + new Object(){}.getClass().getEnclosingMethod().getName() + ", in " + this.getClass().getName());
		/* Make Answer() and set Object values */
		Answer answer = new Answer();
		answer.setCreateDate(LocalDateTime.now());
		answer.setContent(content);
		answer.setQuestion(question);
		/* Saving answer using answerRepository(), kind of interface */
		answerRepository.save(answer);
	}
}
