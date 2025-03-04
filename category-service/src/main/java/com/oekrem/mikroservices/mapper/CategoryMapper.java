package com.oekrem.mikroservices.mapper;

import com.oekrem.mikroservices.dto.CategoryResponse;
import com.oekrem.mikroservices.dto.CreateCategoryRequest;
import com.oekrem.mikroservices.dto.PatchCategoryRequest;
import com.oekrem.mikroservices.dto.UpdateCategoryRequest;
import com.oekrem.mikroservices.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CategoryMapper {

    Category toCategoryFromCreateRequest(CreateCategoryRequest createCategoryRequest);
    Category toCategoryFromUpdateRequest(UpdateCategoryRequest updateCategoryRequest);
    CategoryResponse toResponse(Category category);

    @Mapping(target = "id", ignore = true)
    void patchCategoryFromRequest(PatchCategoryRequest patchCategoryRequest, @MappingTarget Category category);

}
