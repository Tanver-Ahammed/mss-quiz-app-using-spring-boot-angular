package com.exam.portal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author: Md. Tanver Ahammed,
 * ICT, MBSTU
 */

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ApiResponse {

    private String message;
    private boolean success;

}
