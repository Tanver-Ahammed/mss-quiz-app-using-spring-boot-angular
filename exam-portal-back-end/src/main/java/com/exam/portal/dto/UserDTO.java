package com.exam.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String phone;

    private String password;

    private String about;

    private boolean isEnable;

    private Set<RoleDTO> roleDTOS = new HashSet<>();

}
