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
public class QuestionDTO {

    private long id;

    private String content;

    private String image;

    private String answer;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private QuizDTO quizDTO;

}
