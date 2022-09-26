package com.exam.portal.controller;

import com.exam.portal.dto.quiz.QuizDTO;
import com.exam.portal.dto.quiz.UserSubmitQuizResultDTO;
import com.exam.portal.services.impl.UserSubmitQuizResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(path = "/user/submit/quiz")
@CrossOrigin("http://localhost:4200/")
public class UserSubmitQuizResultController {

    @Autowired
    private UserSubmitQuizResultServiceImpl userSubmitQuizResultService;

    // save user submit quiz
    @PostMapping
    public ResponseEntity<UserSubmitQuizResultDTO> saveSerSubmitQuizResult(
            @RequestBody QuizDTO quizDTO, Principal principal) {
        String username = principal.getName();

        UserSubmitQuizResultDTO userSubmitQuizResultDTO =
                this.userSubmitQuizResultService.saveUserSubmitQuizResult(quizDTO, username);
        return ResponseEntity.ok(userSubmitQuizResultDTO);
    }

    // get user submit quiz by id
    @GetMapping(path = "/{userSubmitQuizResultId}")
    public ResponseEntity<UserSubmitQuizResultDTO> getUserSubmitQuizResultById(
            @PathVariable("userSubmitQuizResultId") Long userSubmitQuizResultId) {
        return ResponseEntity.ok(this.userSubmitQuizResultService.getSingleUserSubmitQuizById(userSubmitQuizResultId));
    }

}
