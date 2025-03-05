package com.oekrem.mikroservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(name = "Create Category Request Model")
public record CreateCategoryRequest(
        @NotBlank(message = "Name is required")
        @Size(min = 1, max = 50, message = "Name mush be between {min} and {max} characters")
        @Schema(name = "name", example = "Science")
        String name,
        @NotBlank(message = "Description is required")
        @Size(min = 5, max = 200, message = "Name mush be between {min} and {max} characters")
        @Schema(name = "description", example = "About space")
        String description
) {
}
