package com.exam.portal.dto.quiz;

import com.exam.portal.entities.quiz.Question;
import com.exam.portal.entities.quiz.UserSubmitQuizResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserQuestionAnswerStoreDTO {

    private Long id;

    private String answer;

    private QuestionDTO questionDTO;

    private UserSubmitQuizResult userSubmitQuizResult;


}