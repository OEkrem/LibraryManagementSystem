package com.oekrem.mikroservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
@Schema(name = "Update User Request Model")
public record UpdateUserRequest (

        @NotBlank(message = "Username is required")
        @Schema(name = "username", example = "username")
        String username,
        @NotBlank(message = "Password is required")
        @Schema(name = "password", example = "password")
        String password,
        @Schema(name = "firstName", example = "first")
        String firstName,
        @Schema(name = "lastName", example = "last")
        String lastName,
        @NotBlank(message = "Email is required")
        @Schema(name = "email", example = "email@example.com")
        String email,
        @Schema(name = "phone", example = "532 321 32 32")
        String phone

){
}
