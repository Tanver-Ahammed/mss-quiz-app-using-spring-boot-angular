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
public class QuizDTO {

    private long id;

    private String title;

    private String description;

    private int maxMarks;

    private int numberOfQuestions;

    private boolean isActive = false;

    private String author;

    private CategoryDTO categoryDTO;

    private List<QuestionDTO> questionDTOS = new ArrayList<>();

}
