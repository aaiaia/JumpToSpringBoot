package com.mysite.sbb.question;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

	public Page<Question> getList(int page){
		int numOfListInPage = 10;
		List<Sort.Order> sort = new ArrayList();
		sort.add(Sort.Order.desc("createDate"));
		Pageable pageable = PageRequest.of(page, numOfListInPage, Sort.by(sort));
		return this.questionRepository.findAll(pageable);
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

	public void create(String subject, String content) {
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		questionRepository.save(q);
	}
}
