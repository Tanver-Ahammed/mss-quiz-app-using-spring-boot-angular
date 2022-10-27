package com.exam.portal.services.impl;

import com.exam.portal.dto.UserDTO;
import com.exam.portal.dto.quiz.QuizDTO;
import com.exam.portal.dto.quiz.UserQuestionAnswerStoreDTO;
import com.exam.portal.dto.quiz.UserSubmitQuizResultDTO;
import com.exam.portal.entities.Role;
import com.exam.portal.entities.User;
import com.exam.portal.entities.quiz.Question;
import com.exam.portal.entities.quiz.Quiz;
import com.exam.portal.entities.quiz.UserQuestionAnswerStore;
import com.exam.portal.entities.quiz.UserSubmitQuizResult;
import com.exam.portal.exception.ResourceNotFoundException;
import com.exam.portal.repositories.UserRepository;
import com.exam.portal.repositories.UserSubmitQuizResultRepository;
import com.exam.portal.services.UserSubmitQuizResultService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author: Md. Tanver Ahammed,
 * ICT, MBSTU
 */

@Service
public class UserSubmitQuizResultServiceImpl implements UserSubmitQuizResultService {

    @Autowired
    private UserSubmitQuizResultRepository userSubmitQuizResultRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private QuizServiceImpl quizService;

    @Autowired
    private QuestionServiceImpl questionService;

    @Autowired
    private UserQuestionAnswerStoreServiceImpl userQuestionAnswerStoreService;

    @Autowired
    private RoleServiceImpl roleService;

    @Autowired
    private ModelMapper modelMapper;

    // user submit quiz save
    @Override
    public UserSubmitQuizResultDTO saveUserSubmitQuizResult(QuizDTO quizDTO, String username) {
        if (!quizDTO.isActive())
            throw new RuntimeException("Your Quiz is not Active");
        UserSubmitQuizResult userSubmitQuizResult = new UserSubmitQuizResult();
        List<UserQuestionAnswerStore> userQuestionAnswerStores = new ArrayList<>();
        int correctAns = 0;
        for (int i = 0; i < quizDTO.getQuestionDTOS().size(); i++) {
            long questionId = quizDTO.getQuestionDTOS().get(i).getId();
            Question question = this.questionService.getQuestionById(questionId);
            if (quizDTO.getQuestionDTOS().get(i).getAnswer() != null && quizDTO.getQuestionDTOS().get(i).getAnswer().equals(question.getAnswer())) {
                ++correctAns;
            }
            UserQuestionAnswerStore userQuestionAnswerStore = new UserQuestionAnswerStore();
            userQuestionAnswerStore.setUserSubmitQuizResult(userSubmitQuizResult);
            userQuestionAnswerStore.setUserAnswer(quizDTO.getQuestionDTOS().get(i).getAnswer());
            userQuestionAnswerStore.setQuestion(question);
            userQuestionAnswerStores.add(userQuestionAnswerStore);
        }

        // set user submit quiz result
        userSubmitQuizResult.setUserQuestionAnswerStores(userQuestionAnswerStores);
        userSubmitQuizResult.setQuiz(this.quizService.quizDTOToQuiz(quizDTO));
        userSubmitQuizResult.setCorrectQuestions(correctAns);

        // set user
        User user = this.userRepository.findByUsername(username).orElseThrow(() ->
                new ResourceNotFoundException("User", username, -1));
        userSubmitQuizResult.setUser(user);
        return this.userSubmitQuizResultToDTO(this.userSubmitQuizResultRepository.save(userSubmitQuizResult));
    }

    // fetch user submit quiz by id
    @Override
    public UserSubmitQuizResultDTO getSingleUserSubmitQuizById(Long usqId, Principal principal) {
        UserDTO userDTO = this.userService.getUserByUsername(principal.getName());
        UserSubmitQuizResult userSubmitQuizResult = this.getUserSubmitQuizResultById(usqId);
        Role role = this.roleService.getRoleById(3L);
        if (userDTO.getRoles().contains(role) &&
                !Objects.equals(userDTO.getId(), userSubmitQuizResult.getUser().getId())) {
            throw new RuntimeException("You are not valid user");
        }
        return this.userSubmitQuizResultToDTO(userSubmitQuizResult);
    }

    // fetch user submit quiz by user
    @Override
    public List<UserSubmitQuizResultDTO> getUserSubmitQuizByUser(Long userId) {
        return null;
    }

    // fetch user submit quiz by quiz
    @Override
    public List<UserSubmitQuizResultDTO> getAllUserSubmitQuizByQuiz(Long quizId) {
        Quiz quiz = this.quizService.getQuizById(quizId);
        List<UserSubmitQuizResultDTO> usqResultDTOS = new ArrayList<>();
        for (UserSubmitQuizResult usqResult : this.userSubmitQuizResultRepository.findByQuiz(quiz)) {
            usqResult.setUserQuestionAnswerStores(null);
            usqResultDTOS.add(this.userSubmitQuizResultToDTO(usqResult));
        }
        return usqResultDTOS;
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
        QuizDTO quizDTO = this.quizService.quizToQuizDTO(userSubmitQuizResult.getQuiz());
        quizDTO.setQuestionDTOS(null);
        userSubmitQuizDTO.setQuizDTO(quizDTO);
        if (userSubmitQuizResult.getUserQuestionAnswerStores() != null) {
            List<UserQuestionAnswerStoreDTO> userQuestionAnswerStoreDTOS =
                    userSubmitQuizResult
                            .getUserQuestionAnswerStores()
                            .stream()
                            .map(uqaStore -> this.userQuestionAnswerStoreService.UserQuestionAnswerStoreServiceToDTO(uqaStore))
                            .collect(Collectors.toList());
            userSubmitQuizDTO.setUserQuestionAnswerStoreDTOS(userQuestionAnswerStoreDTOS);
        }
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
