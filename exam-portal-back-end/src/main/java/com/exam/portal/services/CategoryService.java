package com.exam.portal.services;

import com.exam.portal.dto.quiz.CategoryDTO;

import java.util.List;

/**
 * @Author: Md. Tanver Ahammed,
 * ICT, MBSTU
 */

public interface CategoryService {

    CategoryDTO addCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getAllCategories();

    CategoryDTO getSingleCategory(Long categoryId);

    void deleteCategory(Long categoryId);

}
