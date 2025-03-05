package com.oekrem.mikroservices.service;

import com.oekrem.mikroservices.dto.CategoryResponse;
import com.oekrem.mikroservices.dto.CreateCategoryRequest;
import com.oekrem.mikroservices.dto.PatchCategoryRequest;
import com.oekrem.mikroservices.dto.UpdateCategoryRequest;
import com.oekrem.mikroservices.exception.CategoryNotFoundException;
import com.oekrem.mikroservices.mapper.CategoryMapper;
import com.oekrem.mikroservices.model.Category;
import com.oekrem.mikroservices.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public Page<CategoryResponse> findAll(int page, int size, String name) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Category> categories;

        if(name != null)
            categories = categoryRepository.findByNameContaining(pageable, name);
        else
            categories = categoryRepository.findAll(pageable);

        return categories.map(categoryMapper::toResponse);
    }

    @Override
    public CategoryResponse findById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));
        return categoryMapper.toResponse(category);
    }

    @Override
    public CategoryResponse save(CreateCategoryRequest createCategoryRequest) {
        Category category = categoryMapper.toCategoryFromCreateRequest(createCategoryRequest);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory);
    }

    @Override
    public CategoryResponse update(Long id, UpdateCategoryRequest updateCategoryRequest) {
        Category category = categoryMapper.toCategoryFromUpdateRequest(updateCategoryRequest);
        category.setId(id);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory);
    }

    @Override
    public void deleteById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryResponse patch(Long id, PatchCategoryRequest patchCategoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow( () -> new CategoryNotFoundException("Category not found with id " + id));

        categoryMapper.patchCategoryFromRequest(patchCategoryRequest, category);

        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory);
    }

}
