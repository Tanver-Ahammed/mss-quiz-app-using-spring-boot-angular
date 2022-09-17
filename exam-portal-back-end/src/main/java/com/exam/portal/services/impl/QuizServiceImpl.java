package com.exam.portal.services.impl;

import com.exam.portal.dto.UserDTO;
import com.exam.portal.dto.quiz.CategoryDTO;
import com.exam.portal.dto.quiz.QuestionDTO;
import com.exam.portal.dto.quiz.QuizDTO;
import com.exam.portal.entities.User;
import com.exam.portal.entities.quiz.Category;
import com.exam.portal.entities.quiz.Question;
import com.exam.portal.entities.quiz.Quiz;
import com.exam.portal.exception.ResourceNotFoundException;
import com.exam.portal.repositories.QuizRepository;
import com.exam.portal.services.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private CategoryServiceImpl categoryService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public QuizDTO addQuiz(QuizDTO quizDTO) {
        return this.quizToQuizDTO(this.quizRepository.save(this.quizDTOToQuiz(quizDTO)));
    }

    @Override
    public QuizDTO updateQuiz(QuizDTO quizDTO) {
        return this.quizToQuizDTO(this.quizRepository.save(this.quizDTOToQuiz(quizDTO)));
    }

    @Override
    public List<QuizDTO> getAllCategories() {
        return this.quizRepository
                .findAll()
                .stream()
                .map(this::quizToQuizDTO)
                .collect(Collectors.toList());
    }

    @Override
    public QuizDTO getSingleQuiz(Long quizId) {
        return this.quizToQuizDTO(this.getQuizById(quizId));
    }

    @Override
    public List<QuizDTO> getAllQuizzesByCategoryId(Long categoryId) {
        Category category = this.categoryService.getCategoryById(categoryId);
        return this.quizRepository
                .findByCategory(category)
                .stream()
                .map(this::quizToQuizDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<QuizDTO> getAllQuizzesByUserId(Long userId) {
        User user = this.userService.getUserById(userId);
        return this.quizRepository
                .findByUser(user)
                .stream()
                .map(this::quizToQuizDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteQuiz(Long quizId) {
        this.quizRepository.delete(this.getQuizById(quizId));
    }

    // get quiz by id
    public Quiz getQuizById(Long quizId) {
        return this.quizRepository.findById(quizId).orElseThrow(() ->
                new ResourceNotFoundException("Quiz", "id", quizId));
    }

    // quiz to quizDTO
    public QuizDTO quizToQuizDTO(Quiz quiz) {
        QuizDTO quizDTO = this.modelMapper.map(quiz, QuizDTO.class);
        CategoryDTO categoryDTO = this.modelMapper.map(quiz.getCategory(), CategoryDTO.class);
        UserDTO userDTO = this.modelMapper.map(quiz.getUser(), UserDTO.class);
        List<QuestionDTO> questionDTOS = quiz
                .getQuestions()
                .stream()
                .map(question -> this.modelMapper.map(question, QuestionDTO.class))
                .collect(Collectors.toList());
        quizDTO.setQuestionDTOS(questionDTOS);
        quizDTO.setCategoryDTO(categoryDTO);
        quizDTO.setUserDTO(userDTO);
        System.err.println(quiz.getUser().getUsername());
        return quizDTO;
    }

    // quizDTO to quiz
    public Quiz quizDTOToQuiz(QuizDTO quizDTO) {
        Quiz quiz = this.modelMapper.map(quizDTO, Quiz.class);
        Category category = this.modelMapper.map(quizDTO.getCategoryDTO(), Category.class);
        List<Question> questions = quizDTO
                .getQuestionDTOS()
                .stream()
                .map(question -> this.modelMapper.map(question, Question.class))
                .collect(Collectors.toList());
        quiz.setQuestions(questions);
        quiz.setCategory(category);
        return quiz;
    }

}
