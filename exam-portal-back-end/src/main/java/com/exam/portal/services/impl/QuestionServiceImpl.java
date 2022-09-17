package com.exam.portal.services.impl;

import com.exam.portal.dto.quiz.QuestionDTO;
import com.exam.portal.dto.quiz.QuizDTO;
import com.exam.portal.entities.quiz.Question;
import com.exam.portal.entities.quiz.Quiz;
import com.exam.portal.exception.ResourceNotFoundException;
import com.exam.portal.repositories.QuestionRepository;
import com.exam.portal.services.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizServiceImpl quizService;

    @Autowired
    private ModelMapper modelMapper;

    // add question
    @Override
    public QuestionDTO addQuestion(QuestionDTO questionDTO) {
        return this.questionToQuestionDTO(this.questionRepository
                .save(this.questionDTOToQuestion(questionDTO)));
    }

    // update question
    @Override
    public QuestionDTO updateQuestion(QuestionDTO questionDTO) {
        return this.questionToQuestionDTO(this.questionRepository
                .save(this.questionDTOToQuestion(questionDTO)));
    }

    // get all question
    @Override
    public List<QuestionDTO> getAllCategories() {
        return this.questionRepository
                .findAll()
                .stream()
                .map(this::questionToQuestionDTO)
                .collect(Collectors.toList());
    }

    // get single question by id
    @Override
    public QuestionDTO getSingleQuestion(Long questionId) {
        return this.questionToQuestionDTO(this.getQuestionById(questionId));
    }

    // get all question by quiz
    @Override
    public List<QuestionDTO> getQuestionByQuizId(Long quizId) {
        Quiz quiz = this.quizService.getQuizById(quizId);
        return this.questionRepository
                .findByQuiz(quiz)
                .stream()
                .map(this::questionToQuestionDTO)
                .collect(Collectors.toList());
    }

    // delete question
    @Override
    public void deleteQuestion(Long questionId) {
        this.questionRepository.delete(this.getQuestionById(questionId));
    }

    // get question by id
    public Question getQuestionById(Long questionId) {
        return this.questionRepository.findById(questionId).orElseThrow(() ->
                new ResourceNotFoundException("Question", "id", questionId));
    }

    // question to questionDTO
    public QuestionDTO questionToQuestionDTO(Question question) {
        QuestionDTO questionDTO = this.modelMapper.map(question, QuestionDTO.class);
        QuizDTO quizDTO = this.modelMapper.map(question.getQuiz(), QuizDTO.class);
        questionDTO.setQuizDTO(quizDTO);
        return questionDTO;
    }

    // questionDTO to question
    public Question questionDTOToQuestion(QuestionDTO questionDTO) {
        Question question = this.modelMapper.map(questionDTO, Question.class);
        Quiz quiz = this.modelMapper.map(questionDTO.getQuizDTO(), Quiz.class);
        question.setQuiz(quiz);
        return question;
    }

}
