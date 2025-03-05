package com.oekrem.mikroservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Patch Category Request Model")
public record PatchCategoryRequest(

        @Schema(name = "name", example = "Science")
        String name,
        @Schema(name = "description", example = "About space")
        String description
) {
}
