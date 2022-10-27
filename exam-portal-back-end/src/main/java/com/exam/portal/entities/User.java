package com.exam.portal.entities;

import com.exam.portal.entities.quiz.UserSubmitQuizResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: Md. Tanver Ahammed,
 * ICT, MBSTU
 */

@Entity
@Table(name = "users",
        indexes = {
                @Index(columnList = "studentId", name = "student_id_index"),
                @Index(columnList = "username", name = "username_index")
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentId;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String batch;

    private String phone;

    private String password;

    private String about;

    private boolean isEnable;

    private String verificationCode;

    // private String role;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<UserSubmitQuizResult> userSubmitQuizResults = new ArrayList<>();

}
