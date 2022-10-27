package com.exam.portal.controller;

import com.exam.portal.dto.ApiResponse;
import com.exam.portal.dto.quiz.CategoryDTO;
import com.exam.portal.services.impl.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Md. Tanver Ahammed,
 * ICT, MBSTU
 */

@RestController
@RequestMapping(path = "/category")
@CrossOrigin("http://localhost:4200/")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;

    // add category
    @PostMapping(path = "/")
    public ResponseEntity<CategoryDTO> addCategory(@RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(this.categoryService.addCategory(categoryDTO), HttpStatus.CREATED);
    }

    // get category
    @GetMapping(path = "/{categoryId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable("categoryId") Long categoryId) {
        return ResponseEntity.ok(this.categoryService.getSingleCategory(categoryId));
    }

    // get all category
    @GetMapping(path = "/")
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        return ResponseEntity.ok(this.categoryService.getAllCategories());
    }

    // update category
    @PutMapping(path = "/")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(this.categoryService.updateCategory(categoryDTO));
    }

    // delete category
    @DeleteMapping(path = "/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Long categoryId) {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Category deleted successfully.", true),
                HttpStatus.OK);
    }

}
