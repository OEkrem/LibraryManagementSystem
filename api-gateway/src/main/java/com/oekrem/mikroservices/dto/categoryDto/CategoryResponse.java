package com.oekrem.mikroservices.dto.categoryDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Category Response Model")
public record CategoryResponse (

        @Schema(name = "id", example = "1")
        Long id,
        @Schema(name = "name", example = "Science")
        String name,
        @Schema(name = "description", example = "About Space")
        String description
) {
}
