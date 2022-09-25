package com.exam.portal.services;

import com.exam.portal.dto.quiz.QuizDTO;
import com.exam.portal.dto.quiz.UserSubmitQuizResultDTO;

public interface UserSubmitQuizResultService {

    // user submit quiz save
    UserSubmitQuizResultDTO saveUserSubmitQuizResult(QuizDTO quizDTO, String username);

    // fetch user submit quiz by id
    UserSubmitQuizResultDTO getSingleUserSubmitQuizById(Long usqId);

    // fetch user submit quiz by user
    UserSubmitQuizResultDTO getUserSubmitQuizByUser(Long userId);

    // fetch user submit quiz by user
    UserSubmitQuizResultDTO getUserSubmitQuizByQuiz(Long quizId);

    // update user submit quiz
    UserSubmitQuizResultDTO updateUserSubmitQuiz(UserSubmitQuizResultDTO userSubmitQuizResultDTO);

}
