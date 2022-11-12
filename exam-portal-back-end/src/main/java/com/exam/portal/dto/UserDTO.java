package com.exam.portal.dto;

import com.exam.portal.dto.quiz.UserSubmitQuizResultDTO;
import com.exam.portal.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author: Md. Tanver Ahammed,
 * ICT, MBSTU
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

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

    private Set<Role> roles = new HashSet<>();

    private List<UserSubmitQuizResultDTO> userSubmitQuizResultDTOS = new ArrayList<>();

//    public Set<Role> getRoles() {
//        Set<Role> roleSet = new HashSet<>();
//        for (Role role : roles) {
//            role.setRoleName(role.getRoleName().substring(5));
//            roleSet.add(role);
//        }
//        return roleSet;
//    }

}
