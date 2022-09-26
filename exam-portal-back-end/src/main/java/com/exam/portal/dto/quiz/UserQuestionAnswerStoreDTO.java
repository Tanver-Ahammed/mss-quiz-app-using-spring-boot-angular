package com.exam.portal.dto.quiz;

import com.exam.portal.entities.quiz.UserSubmitQuizResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserQuestionAnswerStoreDTO {

    private Long id;

    private String userAnswer;

    private QuestionDTO questionDTO;

    private UserSubmitQuizResultDTO userSubmitQuizResultDTO;


}