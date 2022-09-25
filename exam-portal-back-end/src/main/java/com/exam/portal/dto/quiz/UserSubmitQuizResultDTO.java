package com.exam.portal.dto.quiz;

import com.exam.portal.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSubmitQuizResultDTO {

    private Long id;

    private int correctQuestions;

    private int quizDuration;

    private UserDTO userDTO;

    private QuizDTO quizDTO;

    private List<UserQuestionAnswerStoreDTO> userQuestionAnswerStoreDTOS = new ArrayList<>();

}