package com.exam.portal.dto.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Md. Tanver Ahammed,
 * ICT, MBSTU
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizDTO {

    private long id;

    private String title;

    private String description;

    private int maxMarks;

    private int numberOfQuestions;

    private String pin;

    private boolean isActive = false;

    private String author;

    private CategoryDTO categoryDTO;

    private List<QuestionDTO> questionDTOS = new ArrayList<>();

    private List<UserSubmitQuizResultDTO> userSubmitQuizResultDTOS = new ArrayList<>();

}
