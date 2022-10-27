package com.exam.portal.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author: Md. Tanver Ahammed,
 * ICT, MBSTU
 */

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