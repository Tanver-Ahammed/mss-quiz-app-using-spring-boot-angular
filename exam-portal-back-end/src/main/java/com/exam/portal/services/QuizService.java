package com.exam.portal.services;

import com.exam.portal.dto.quiz.QuizDTO;

import java.security.Principal;
import java.util.List;

/**
 * @Author: Md. Tanver Ahammed,
 * ICT, MBSTU
 */

public interface QuizService {

    QuizDTO addQuiz(QuizDTO quizDTO);

    QuizDTO updateQuiz(QuizDTO quizDTO, Principal principal);

    List<QuizDTO> getAllQuizzes();

    QuizDTO getSingleQuiz(Long quizId);

    QuizDTO getSingleQuizForStartingQuiz(Long quizId, Principal principal);

    List<QuizDTO> getAllQuizzesByCategoryId(Long categoryId);

    List<QuizDTO> getAllQuizzesByUsername(String username);

    List<QuizDTO> getAllActiveQuizzes();

    List<QuizDTO> getAllActiveQuizzesByCategory(Long categoryId);

    void deleteQuiz(Long quizId, Principal principal);

}
