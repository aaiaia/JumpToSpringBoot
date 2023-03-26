package com.mysite.sbb.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/* Question 오브젝트트의 처리를 위해 JpaRepository 상속 및 제네릭으로, 다음엔 Primary Key인 Id 의 속성을 넣음 */
public interface QuestionRepository extends JpaRepository<Question, Integer>{
	List<Question> findBySubject(String subject);
	List<Question> findBySubjectAndContent(String subject, String content);
	List<Question> findBySubjectLike(String subject);
}