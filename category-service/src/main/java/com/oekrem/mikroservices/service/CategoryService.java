package com.oekrem.mikroservices.service;

import com.oekrem.mikroservices.dto.CategoryResponse;
import com.oekrem.mikroservices.dto.CreateCategoryRequest;
import com.oekrem.mikroservices.dto.UpdateCategoryRequest;

import java.util.List;

public interface CategoryService {

    List<CategoryResponse> findAll();
    CategoryResponse findById(int id);
    CategoryResponse save(CreateCategoryRequest createCategoryRequest);
    CategoryResponse update(int id, UpdateCategoryRequest updateCategoryRequest);
    void deleteById(int id);
    CategoryResponse patch(int id, UpdateCategoryRequest updateCategoryRequest);

}
