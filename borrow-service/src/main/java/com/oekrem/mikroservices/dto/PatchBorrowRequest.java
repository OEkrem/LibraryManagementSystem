package com.oekrem.mikroservices.dto;

import com.oekrem.mikroservices.model.BorrowStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record PatchBorrowRequest (

        @Schema(name = "userId", example = "1")
        Long userId,
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
