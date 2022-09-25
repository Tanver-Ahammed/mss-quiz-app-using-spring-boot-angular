package com.exam.portal.controller;

import com.exam.portal.dto.UserDTO;
import com.exam.portal.dto.quiz.QuizDTO;
import com.exam.portal.dto.quiz.UserSubmitQuizResultDTO;
import com.exam.portal.services.impl.UserServiceImpl;
import com.exam.portal.services.impl.UserSubmitQuizResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping(path = "/user/submit/quiz")
public class UserSubmitQuizResultController {

    @Autowired
    private UserSubmitQuizResultServiceImpl userSubmitQuizResultService;

    @PostMapping
    public ResponseEntity<UserSubmitQuizResultDTO> saveSerSubmitQuizResult(
            @RequestBody QuizDTO quizDTO, Principal principal) {
        String username = principal.getName();

        UserSubmitQuizResultDTO userSubmitQuizResultDTO =
                this.userSubmitQuizResultService.saveUserSubmitQuizResult(quizDTO, username);
        return ResponseEntity.ok(userSubmitQuizResultDTO);
    }

}
