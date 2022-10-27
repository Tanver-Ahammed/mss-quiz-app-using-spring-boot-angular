package com.exam.portal.repositories;

import com.exam.portal.entities.quiz.UserQuestionAnswerStore;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Md. Tanver Ahammed,
 * ICT, MBSTU
 */

public interface UserQuestionAnswerStoreRepository extends JpaRepository<UserQuestionAnswerStore, Long> {
}
