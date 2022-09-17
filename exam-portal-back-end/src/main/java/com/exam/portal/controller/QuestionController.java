package com.exam.portal.controller;

import com.exam.portal.dto.ApiResponse;
import com.exam.portal.dto.quiz.QuestionDTO;
import com.exam.portal.services.impl.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/question")
@CrossOrigin("http://localhost:4200/")
public class QuestionController {

    @Autowired
    private QuestionServiceImpl questionService;

    // add question
    @PostMapping(path = "/")
    public ResponseEntity<QuestionDTO> addQuestion(@RequestBody QuestionDTO questionDTO) {
        return new ResponseEntity<>(this.questionService.addQuestion(questionDTO), HttpStatus.CREATED);
    }

    // get question
    @GetMapping(path = "/{questionId}")
    public ResponseEntity<QuestionDTO> getQuestionById(@PathVariable("questionId") Long questionId) {
        return ResponseEntity.ok(this.questionService.getSingleQuestion(questionId));
    }

    // get all question
    @GetMapping(path = "/")
    public ResponseEntity<List<QuestionDTO>> getAllCategories() {
        return ResponseEntity.ok(this.questionService.getAllCategories());
    }

    // get all questions by category
    @GetMapping(path = "/quiz/{quizId}")
    public ResponseEntity<List<QuestionDTO>> getAllQuestionsByCategory(@PathVariable("quizId") Long quizId) {
        return ResponseEntity.ok(this.questionService.getQuestionByQuizId(quizId));
    }

    // update question
    @PutMapping(path = "/")
    public ResponseEntity<QuestionDTO> updateQuestion(@RequestBody QuestionDTO questionDTO) {
        return ResponseEntity.ok(this.questionService.updateQuestion(questionDTO));
    }

    // delete question
    @DeleteMapping(path = "/{questionId}")
    public ResponseEntity<ApiResponse> deleteQuestion(@PathVariable("questionId") Long questionId) {
        this.questionService.deleteQuestion(questionId);
        return new ResponseEntity<>(new ApiResponse("Question deleted successfully.", true),
                HttpStatus.OK);
    }

}
