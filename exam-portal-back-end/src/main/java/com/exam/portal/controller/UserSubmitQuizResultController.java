package com.exam.portal.controller;

import com.exam.portal.dto.quiz.QuizDTO;
import com.exam.portal.dto.quiz.UserSubmitQuizResultDTO;
import com.exam.portal.services.impl.UserSubmitQuizResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * @Author: Md. Tanver Ahammed,
 * ICT, MBSTU
 */

@RestController
@RequestMapping(path = "/user/submit/")
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
            @PathVariable("userSubmitQuizResultId") Long userSubmitQuizResultId, Principal principal) {
        return ResponseEntity.ok(this.userSubmitQuizResultService.getSingleUserSubmitQuizById(userSubmitQuizResultId, principal));
    }

    // get all user submit result by quiz id
    @GetMapping(path = "/quiz/{quizId}")
    public ResponseEntity<List<UserSubmitQuizResultDTO>> getUserSubmitQuizResultByQuizId(
            @PathVariable("quizId") Long quizId) {
        return ResponseEntity.ok(this.userSubmitQuizResultService.getAllUserSubmitQuizByQuiz(quizId));
    }

}
