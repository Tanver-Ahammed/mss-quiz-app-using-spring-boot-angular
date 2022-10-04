package com.exam.portal.repositories;

import com.exam.portal.entities.User;
import com.exam.portal.entities.quiz.Quiz;
import com.exam.portal.entities.quiz.UserSubmitQuizResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserSubmitQuizResultRepository extends JpaRepository<UserSubmitQuizResult, Long> {

    List<UserSubmitQuizResult> findByUser(User user);

    List<UserSubmitQuizResult> findByQuiz(Quiz quiz);

    Boolean existsByUserAndQuiz(User user, Quiz quiz);

}
