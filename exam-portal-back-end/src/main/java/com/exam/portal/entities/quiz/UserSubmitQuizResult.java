package com.exam.portal.entities.quiz;

import com.exam.portal.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Md. Tanver Ahammed,
 * ICT, MBSTU
 */

@Entity
@Table(name = "user_submit_quiz_results")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSubmitQuizResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int correctQuestions;

    private int quizDuration;

    @ManyToOne
    @JoinColumn(name = "user_id_fk", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id_fk", referencedColumnName = "id")
    private Quiz quiz;

    @OneToMany(mappedBy = "userSubmitQuizResult", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<UserQuestionAnswerStore> userQuestionAnswerStores = new ArrayList<>();

}