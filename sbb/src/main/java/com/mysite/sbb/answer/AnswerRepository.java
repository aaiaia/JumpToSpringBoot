package com.mysite.sbb.answer;

import org.springframework.data.jpa.repository.JpaRepository;

/* Answer 오브젝트트의 처리를 위해 JpaRepository 상속 및 제네릭으로, 다음엔 Primary Key인 Id 의 속성을 넣음 */
public interface AnswerRepository extends JpaRepository<Answer, Integer>{

}
