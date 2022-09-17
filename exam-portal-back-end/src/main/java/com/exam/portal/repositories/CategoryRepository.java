package com.exam.portal.repositories;

import com.exam.portal.entities.quiz.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
