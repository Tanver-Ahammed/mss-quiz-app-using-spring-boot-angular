package com.exam.portal.entities.quiz;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 5000)
    private String content;

    private String image;

    private String answer;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    @ManyToOne
    @JoinColumn(name = "quiz_id_fk", referencedColumnName = "id")
    private Quiz quiz;

}
