package com.exam.portal.services;

import com.exam.portal.dto.quiz.QuizDTO;

import java.security.Principal;
import java.util.List;

public interface QuizService {

    QuizDTO addQuiz(QuizDTO quizDTO);

    QuizDTO updateQuiz(QuizDTO quizDTO);

    List<QuizDTO> getAllCategories();

    QuizDTO getSingleQuiz(Long quizId);

    QuizDTO getSingleQuizForStartingQuiz(Long quizId, Principal principal);

    List<QuizDTO> getAllQuizzesByCategoryId(Long categoryId);

    List<QuizDTO> getAllQuizzesByUserId(String username);

    List<QuizDTO> getAllActiveQuizzes();

    List<QuizDTO> getAllActiveQuizzesByCategory(Long categoryId);

    void deleteQuiz(Long quizId);

}
