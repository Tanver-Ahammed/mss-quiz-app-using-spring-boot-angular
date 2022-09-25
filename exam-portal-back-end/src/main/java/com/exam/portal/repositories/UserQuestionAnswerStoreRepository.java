package com.exam.portal.repositories;

import com.exam.portal.entities.quiz.UserQuestionAnswerStore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuestionAnswerStoreRepository extends JpaRepository<UserQuestionAnswerStore, Long> {
}
