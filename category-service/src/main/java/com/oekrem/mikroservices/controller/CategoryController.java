package com.oekrem.mikroservices.controller;

import com.oekrem.mikroservices.dto.CategoryResponse;
import com.oekrem.mikroservices.dto.CreateCategoryRequest;
import com.oekrem.mikroservices.dto.UpdateCategoryRequest;
import com.oekrem.mikroservices.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CreateCategoryRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable int id, @RequestBody UpdateCategoryRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(id, request));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryResponse> patchCategory(@PathVariable int id, @RequestBody UpdateCategoryRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.patch(id, request));
    }

    @DeleteMapping("/id")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        categoryService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
