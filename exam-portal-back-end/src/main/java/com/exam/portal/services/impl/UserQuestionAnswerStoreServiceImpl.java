package com.exam.portal.services.impl;

import com.exam.portal.dto.quiz.QuestionDTO;
import com.exam.portal.dto.quiz.UserQuestionAnswerStoreDTO;
import com.exam.portal.entities.quiz.UserQuestionAnswerStore;
import com.exam.portal.services.UserQuestionAnswerStoreService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Md. Tanver Ahammed,
 * ICT, MBSTU
 */

@Service
public class UserQuestionAnswerStoreServiceImpl implements UserQuestionAnswerStoreService {

    @Autowired
    private ModelMapper modelMapper;

    // UserQuestionAnswerStoreService to it's dto
    public UserQuestionAnswerStoreDTO UserQuestionAnswerStoreServiceToDTO(UserQuestionAnswerStore userQuestionAnswerStore) {
        UserQuestionAnswerStoreDTO uqaStoreDTO =
                this.modelMapper.map(userQuestionAnswerStore, UserQuestionAnswerStoreDTO.class);
        uqaStoreDTO.setQuestionDTO(this.modelMapper.map(userQuestionAnswerStore.getQuestion(), QuestionDTO.class));
        return uqaStoreDTO;
    }

}
