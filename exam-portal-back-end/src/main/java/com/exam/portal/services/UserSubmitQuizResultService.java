package com.exam.portal.services;

import com.exam.portal.dto.quiz.QuizDTO;
import com.exam.portal.dto.quiz.UserSubmitQuizResultDTO;

import java.security.Principal;
import java.util.List;

public interface UserSubmitQuizResultService {

    // user submit quiz save
    UserSubmitQuizResultDTO saveUserSubmitQuizResult(QuizDTO quizDTO, String username);

    // fetch user submit quiz by id
    UserSubmitQuizResultDTO getSingleUserSubmitQuizById(Long usqId, Principal principal);

    // fetch user submit quiz by user
    List<UserSubmitQuizResultDTO> getUserSubmitQuizByUser(Long userId);

    // fetch user submit quiz by user
    List<UserSubmitQuizResultDTO> getAllUserSubmitQuizByQuiz(Long quizId);

    // update user submit quiz
    UserSubmitQuizResultDTO updateUserSubmitQuiz(UserSubmitQuizResultDTO userSubmitQuizResultDTO);

}
