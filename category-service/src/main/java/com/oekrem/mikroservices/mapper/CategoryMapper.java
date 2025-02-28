package com.oekrem.mikroservices.mapper;

import com.oekrem.mikroservices.dto.CategoryResponse;
import com.oekrem.mikroservices.dto.CreateCategoryRequest;
import com.oekrem.mikroservices.dto.UpdateCategoryRequest;
import com.oekrem.mikroservices.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category toCategoryFromCreateRequest(CreateCategoryRequest createCategoryRequest);
    Category toCategoryFromUpdateRequest(UpdateCategoryRequest updateCategoryRequest);
    CategoryResponse toResponse(Category category);
}
