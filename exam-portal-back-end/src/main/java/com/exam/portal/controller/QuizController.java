package com.exam.portal.controller;

import com.exam.portal.dto.ApiResponse;
import com.exam.portal.dto.quiz.QuizDTO;
import com.exam.portal.services.impl.QuizServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(path = "/quiz")
@CrossOrigin("http://localhost:4200/")
public class QuizController {

    @Autowired
    private QuizServiceImpl quizService;

    // add quiz
    @PostMapping(path = "/")
    public ResponseEntity<QuizDTO> addQuiz(@RequestBody QuizDTO quizDTO) {
        return new ResponseEntity<>(this.quizService.addQuiz(quizDTO), HttpStatus.CREATED);
    }

    // get quiz
    @GetMapping(path = "/{quizId}")
    public ResponseEntity<QuizDTO> getQuizById(@PathVariable("quizId") Long quizId) {
        return ResponseEntity.ok(this.quizService.getSingleQuiz(quizId));
    }

    // get quiz
    @GetMapping(path = "/start/{quizId}")
    public ResponseEntity<QuizDTO> getSingleQuizForStartingQuiz(@PathVariable("quizId") Long quizId, Principal principal) {
        return ResponseEntity.ok(this.quizService.getSingleQuizForStartingQuiz(quizId, principal));
    }

    // get all quiz
    @GetMapping(path = "/")
    public ResponseEntity<List<QuizDTO>> getAllCategories() {
        return ResponseEntity.ok(this.quizService.getAllCategories());
    }

    // get all quizzes by category
    @GetMapping(path = "/category/{categoryId}")
    public ResponseEntity<List<QuizDTO>> getAllQuizzesByCategory(@PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok(this.quizService.getAllQuizzesByCategoryId(categoryId));
    }

    // get all quizzes by user
    @GetMapping(path = "/user/{username}")
    public ResponseEntity<List<QuizDTO>> getAllQuizzesByUser(@PathVariable("username") String username) {
        return ResponseEntity.ok(this.quizService.getAllQuizzesByUserId(username));
    }

    // get all active quizzes for user
    @GetMapping(path = "/active")
    public ResponseEntity<List<QuizDTO>> getAllActiveQuizzes() {
        return ResponseEntity.ok(this.quizService.getAllActiveQuizzes());
    }

    // get all active quizzes by category for user
    @GetMapping(path = "/active/category/{categoryId}")
    public ResponseEntity<List<QuizDTO>> getAllActiveQuizzesByCategory(@PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok(this.quizService.getAllActiveQuizzesByCategory(categoryId));
    }

    // update quiz
    @PutMapping(path = "/")
    public ResponseEntity<QuizDTO> updateQuiz(@RequestBody QuizDTO quizDTO) {
        return ResponseEntity.ok(this.quizService.updateQuiz(quizDTO));
    }

    // delete quiz
    @DeleteMapping(path = "/{quizId}")
    public ResponseEntity<ApiResponse> deleteQuiz(@PathVariable("quizId") Long quizId) {
        this.quizService.deleteQuiz(quizId);
        return new ResponseEntity<>(new ApiResponse("Quiz deleted successfully.", true),
                HttpStatus.OK);
    }

}
