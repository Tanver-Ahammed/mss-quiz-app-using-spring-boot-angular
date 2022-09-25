package com.exam.portal.services.impl;

import com.exam.portal.dto.quiz.QuizDTO;
import com.exam.portal.dto.quiz.UserSubmitQuizResultDTO;
import com.exam.portal.entities.User;
import com.exam.portal.entities.quiz.Question;
import com.exam.portal.entities.quiz.UserQuestionAnswerStore;
import com.exam.portal.entities.quiz.UserSubmitQuizResult;
import com.exam.portal.exception.ResourceNotFoundException;
import com.exam.portal.repositories.UserSubmitQuizResultRepository;
import com.exam.portal.services.UserSubmitQuizResultService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSubmitQuizResultServiceImpl implements UserSubmitQuizResultService {

    @Autowired
    private UserSubmitQuizResultRepository userSubmitQuizResultRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private QuizServiceImpl quizService;

    @Autowired
    private QuestionServiceImpl questionService;

    @Autowired
    private ModelMapper modelMapper;

    // user submit quiz save
    @Override
    public UserSubmitQuizResultDTO saveUserSubmitQuizResult(QuizDTO quizDTO, String username) {
        List<UserQuestionAnswerStore> userQuestionAnswerStores = new ArrayList<>();
        int correctAns = 0;
        for (int i = 0; i < quizDTO.getQuestionDTOS().size(); i++) {
            long questionId = quizDTO.getQuestionDTOS().get(i).getId();
            Question question = this.questionService.getQuestionById(questionId);
            if (quizDTO.getQuestionDTOS().get(i).getAnswer().equals(question.getAnswer())) {
                ++correctAns;
            }
            UserQuestionAnswerStore userQuestionAnswerStore = new UserQuestionAnswerStore();
            userQuestionAnswerStore.setAnswer(quizDTO.getQuestionDTOS().get(i).getAnswer());
            userQuestionAnswerStore.setQuestion(question);
            userQuestionAnswerStores.add(userQuestionAnswerStore);
        }

        // set user submit quiz result
        UserSubmitQuizResult userSubmitQuizResult = new UserSubmitQuizResult();
        userSubmitQuizResult.setUserQuestionAnswerStores(userQuestionAnswerStores);
        userSubmitQuizResult.setQuiz(this.quizService.quizDTOToQuiz(quizDTO));
        userSubmitQuizResult.setCorrectQuestions(correctAns);

        // set user
        User user = this.modelMapper.map(this.userService.getUserByUsername(username), User.class);
        userSubmitQuizResult.setUser(user);
        return this.userSubmitQuizResultToDTO(this.userSubmitQuizResultRepository.save(userSubmitQuizResult));
    }

    // fetch user submit quiz by id
    @Override
    public UserSubmitQuizResultDTO getSingleUserSubmitQuizById(Long usqId) {
        return this.userSubmitQuizResultToDTO(this.getUserSubmitQuizResultById(usqId));
    }

    // fetch user submit quiz by user
    @Override
    public UserSubmitQuizResultDTO getUserSubmitQuizByUser(Long userId) {
        return null;
    }

    // fetch user submit quiz by user
    @Override
    public UserSubmitQuizResultDTO getUserSubmitQuizByQuiz(Long quizId) {
        return null;
    }

    // update user submit quiz
    @Override
    public UserSubmitQuizResultDTO updateUserSubmitQuiz(UserSubmitQuizResultDTO userSubmitQuizResultDTO) {
        return null;
    }

    // get user submit quiz by id
    public UserSubmitQuizResult getUserSubmitQuizResultById(Long usqId) {
        return this.userSubmitQuizResultRepository.findById(usqId).orElseThrow(() ->
                new ResourceNotFoundException("User submit Quiz result", "Id", usqId));
    }

    // convert UserSubmitQuiz to it's DTO
    public UserSubmitQuizResultDTO userSubmitQuizResultToDTO(UserSubmitQuizResult userSubmitQuizResult) {
        UserSubmitQuizResultDTO userSubmitQuizDTO = this.modelMapper.map(userSubmitQuizResult, UserSubmitQuizResultDTO.class);
        userSubmitQuizDTO.setUserDTO(this.userService.userToUserDTO(userSubmitQuizResult.getUser()));
        userSubmitQuizDTO.setQuizDTO(this.quizService.quizToQuizDTO(userSubmitQuizResult.getQuiz()));
        return userSubmitQuizDTO;
    }

    // convert UserSubmitQuizDTO to it's UserSubmitQuiz
    public UserSubmitQuizResult dtoToUserSubmitQuizResult(UserSubmitQuizResultDTO userSubmitQuizDTO) {
        UserSubmitQuizResult userSubmitQuizResult = this.modelMapper.map(userSubmitQuizDTO, UserSubmitQuizResult.class);
        userSubmitQuizResult.setUser(this.modelMapper.map(userSubmitQuizDTO.getUserDTO(), User.class));
        userSubmitQuizResult.setQuiz(this.quizService.quizDTOToQuiz(userSubmitQuizDTO.getQuizDTO()));
        return userSubmitQuizResult;
    }

}
