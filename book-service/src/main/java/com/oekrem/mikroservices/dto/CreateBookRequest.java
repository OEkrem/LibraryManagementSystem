package com.oekrem.mikroservices.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.UUID;

@Builder
public record CreateBookRequest (

        @NotBlank(message = "Title is required")
        String title,
        @NotBlank(message = "Author is required")
        String author,
        @NotBlank(message = "Publisher is required")
        String publisher,
        @NotNull(message = "Published Year is required")
        Integer publishedDate,

        String pages,
        String language,
        @NotBlank(message = "Description is required")
        String description,

        @NotNull @NotBlank(message = "Category is required")
        UUID category_id,
        Integer stock,
        Float rating,
        String edition

){
}
