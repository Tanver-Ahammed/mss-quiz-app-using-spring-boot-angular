package com.exam.portal.services.impl;

import com.exam.portal.dto.quiz.CategoryDTO;
import com.exam.portal.dto.quiz.QuizDTO;
import com.exam.portal.entities.quiz.Category;
import com.exam.portal.entities.quiz.Quiz;
import com.exam.portal.exception.ResourceNotFoundException;
import com.exam.portal.repositories.CategoryRepository;
import com.exam.portal.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDTO) {
        return this.categoryToCategoryDTO
                (this.categoryRepository.save
                        (this.categoryDTOToCategory(categoryDTO)));
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO) {
        return this.categoryToCategoryDTO
                (this.categoryRepository.save
                        (this.categoryDTOToCategory(categoryDTO)));
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        return this.categoryRepository
                .findAll()
                .stream()
                .map(this::categoryToCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO getSingleCategory(Long categoryId) {
        return this.categoryToCategoryDTO(this.getCategoryById(categoryId));
    }

    @Override
    public void deleteCategory(Long categoryId) {
        this.categoryRepository.delete(this.getCategoryById(categoryId));
    }

    // get category by id
    public Category getCategoryById(Long categoryId) {
        return this.categoryRepository.findById(categoryId).orElseThrow(() ->
                new ResourceNotFoundException("Category", "id", categoryId));
    }

    // category to categoryDTO
    public CategoryDTO categoryToCategoryDTO(Category category) {
        CategoryDTO categoryDTO = this.modelMapper.map(category, CategoryDTO.class);
        List<QuizDTO> quizDTOS = category
                .getQuizzes()
                .stream()
                .map(quiz -> this.modelMapper.map(quiz, QuizDTO.class))
                .collect(Collectors.toList());
        categoryDTO.setQuizDTOS(quizDTOS);
        return categoryDTO;
    }

    // categoryDTO to category
    public Category categoryDTOToCategory(CategoryDTO categoryDTO) {
        Category category = this.modelMapper.map(categoryDTO, Category.class);
        List<Quiz> quizzes = categoryDTO
                .getQuizDTOS()
                .stream()
                .map(quizDTO -> this.modelMapper.map(quizDTO, Quiz.class))
                .collect(Collectors.toList());
        category.setQuizzes(quizzes);
        return category;
    }


}
