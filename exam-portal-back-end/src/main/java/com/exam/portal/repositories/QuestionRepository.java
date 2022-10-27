package com.exam.portal.repositories;

import com.exam.portal.entities.quiz.Question;
import com.exam.portal.entities.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: Md. Tanver Ahammed,
 * ICT, MBSTU
 */

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByQuiz(Quiz quiz);

}
