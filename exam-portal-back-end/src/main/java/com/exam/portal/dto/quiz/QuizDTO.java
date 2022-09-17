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

    private int maxMArks;

    private int numberOfQuestions;

    private boolean isActive = false;

    private UserDTO userDTO;

    private CategoryDTO categoryDTO;

    private List<QuestionDTO> questionDTOS = new ArrayList<>();

}
