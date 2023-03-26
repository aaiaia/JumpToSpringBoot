package com.mysite.sbb.question;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.mysite.sbb.DataNotFoundException;

@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;
	
	public List<Question> getList(){
		System.out.println("called getList(), in " + this.getClass().getName());
		return this.questionRepository.findAll();
	}

	public Question getQuestion(Integer id){
		Optional<Question> oq = this.questionRepository.findById(id);
		if(oq.isPresent()) {
			return oq.get();
		}
		else
		{
			throw new DataNotFoundException("question not found");
		}
	}
}
