package com.oekrem.mikroservices.service;

import com.oekrem.mikroservices.dto.CategoryResponse;
import com.oekrem.mikroservices.dto.CreateCategoryRequest;
import com.oekrem.mikroservices.dto.UpdateCategoryRequest;
import com.oekrem.mikroservices.exception.CategoryNotFoundException;
import com.oekrem.mikroservices.mapper.CategoryMapper;
import com.oekrem.mikroservices.model.Category;
import com.oekrem.mikroservices.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponse> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(categoryMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse findById(int id) {
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
    public CategoryResponse update(int id, UpdateCategoryRequest updateCategoryRequest) {
        Category category = categoryMapper.toCategoryFromUpdateRequest(updateCategoryRequest);
        category.setId(id);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory);
    }

    @Override
    public void deleteById(int id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found with id " + id));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryResponse patch(int id, UpdateCategoryRequest updateCategoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow( () -> new CategoryNotFoundException("Category not found with id " + id));

        if(updateCategoryRequest.name() != null)
            category.setName(updateCategoryRequest.name());
        if(updateCategoryRequest.description() != null)
            category.setDescription(updateCategoryRequest.description());

        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory);
    }

}
