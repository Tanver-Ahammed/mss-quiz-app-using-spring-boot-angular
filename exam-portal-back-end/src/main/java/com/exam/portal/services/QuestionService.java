package com.exam.portal.services;

import com.exam.portal.dto.quiz.QuestionDTO;

import java.util.List;

public interface QuestionService {

    QuestionDTO addQuestion(QuestionDTO questionDTO);

    QuestionDTO updateQuestion(QuestionDTO questionDTO);

    List<QuestionDTO> getAllCategories();

    QuestionDTO getSingleQuestion(Long questionId);

    List<QuestionDTO> getQuestionByQuizId(Long quizId);

    void deleteQuestion(Long questionId);

}
