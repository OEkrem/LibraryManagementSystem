package com.oekrem.mikroservices.service;

import com.oekrem.mikroservices.dto.CategoryResponse;
import com.oekrem.mikroservices.dto.CreateCategoryRequest;
import com.oekrem.mikroservices.dto.PatchCategoryRequest;
import com.oekrem.mikroservices.dto.UpdateCategoryRequest;
import org.springframework.data.domain.Page;

public interface CategoryService {

    Page<CategoryResponse> findAll(int page, int size, String name);
    CategoryResponse findById(Long id);
    CategoryResponse save(CreateCategoryRequest createCategoryRequest);
    CategoryResponse update(Long id, UpdateCategoryRequest updateCategoryRequest);
    void deleteById(Long id);
    CategoryResponse patch(Long id, PatchCategoryRequest patchCategoryRequest);

}
