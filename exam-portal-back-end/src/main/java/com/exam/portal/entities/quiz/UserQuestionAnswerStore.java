package com.exam.portal.entities.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_question_answer_stores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserQuestionAnswerStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userAnswer;

    @OneToOne(orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Question question;

    @ManyToOne
    @JoinColumn(name = "user_submit_quiz_result_fk", referencedColumnName = "id")
    private UserSubmitQuizResult userSubmitQuizResult;


}