package com.exam.portal.entities.quiz;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "quizzes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @Column(length = 5000)
    private String description;

    private int maxMarks;

    private int numberOfQuestions;

    private String pin;

    private boolean isActive = false;

    private String author;

    @ManyToOne
    @JoinColumn(name = "category_id_fk", referencedColumnName = "id")
    private Category category;

    @OneToMany(mappedBy = "quiz", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<UserSubmitQuizResult> userSubmitQuizResults = new ArrayList<>();

}
