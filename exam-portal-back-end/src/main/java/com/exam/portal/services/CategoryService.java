package com.exam.portal.services;

import com.exam.portal.dto.quiz.CategoryDTO;

import java.util.List;

public interface CategoryService {

    CategoryDTO addCategory(CategoryDTO categoryDTO);

    CategoryDTO updateCategory(CategoryDTO categoryDTO);

    List<CategoryDTO> getAllCategories();

    CategoryDTO getSingleCategory(Long categoryId);

    void deleteCategory(Long categoryId);

}
