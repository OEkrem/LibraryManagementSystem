package com.oekrem.mikroservices.dto;

import com.oekrem.mikroservices.model.BorrowStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Schema(name = "Update Borrow Request Model")
public record UpdateBorrowRequest (

        @NotNull(message = "User id s required")
        @Schema(name = "userId", example = "1")
        Long userId,
        @NotNull(message = "Book id is required")
        @Schema(name = "bookId", example = "1")
        Long bookId,
        @Schema(name = "borrow_date", example = "")
        LocalDateTime borrow_date,
        @Schema(name = "return_date", example = "")
        LocalDateTime return_date,

        @Schema(name = "status", example = "AVAILABLE")
        BorrowStatus status
){
}
