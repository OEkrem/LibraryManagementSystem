package com.oekrem.mikroservices.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UpdateCategoryRequest(

        @NotBlank(message = "Name is required")
        @Size(min = 1, max = 50, message = "Name mush be between {min} and {max} characters")
        String name,
        @NotBlank(message = "Description is required")
        @Size(min = 5, max = 200, message = "Name mush be between {min} and {max} characters")
        String description
) {
}
