package com.mysite.sbb.answer;

import java.util.Optional;

import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;

import com.mysite.sbb.DataNotFoundException;

@RequiredArgsConstructor
@Service
public class AnswerService {

	private final AnswerRepository answerRepository;

	public void create(Question question, String content, SiteUser author) {
		/* Just Debugging */
		System.out.println("called: " + new Object(){}.getClass().getEnclosingMethod().getName() + ", in " + this.getClass().getName());
		/* Make Answer() and set Object values */
		Answer answer = new Answer();
		answer.setCreateDate(LocalDateTime.now());
		answer.setContent(content);
		answer.setQuestion(question);
		answer.setAuthor(author);
		/* Saving answer using answerRepository(), kind of interface */
		answerRepository.save(answer);
	}

	public Answer getAnswer(Integer id) {
		Optional<Answer> _answer = this.answerRepository.findById(id);
		if(_answer.isPresent()) {
			return _answer.get();
		} else {
			throw new DataNotFoundException("answer not found");
		}
	}

	public void modify(Answer answer, String content) {
		answer.setContent(content);
		answer.setModifiedDate(LocalDateTime.now());
		this.answerRepository.save(answer);
	}

	public void delete(Answer answer) {
		this.answerRepository.delete(answer);
	}
}
