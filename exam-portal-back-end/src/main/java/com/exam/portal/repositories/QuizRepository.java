package com.exam.portal.repositories;

import com.exam.portal.entities.User;
import com.exam.portal.entities.quiz.Category;
import com.exam.portal.entities.quiz.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findByCategory(Category category);

    List<Quiz> findByAuthor(String username);

    // for user
    List<Quiz> findByIsActive(boolean isActive);

    // for user
    List<Quiz> findByCategoryAndIsActive(Category category, boolean isActive);

}
