package com.oekrem.mikroservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Create Borrow Request Model")
public record CreateBorrowRequest (

        @NotNull(message = "User id is required")
        @Schema(name = "userId", example = "1")
        Long userId,
        @NotNull(message = "Book id is required")
        @Schema(name = "bookId", example = "1")
        Long bookId
){
}
