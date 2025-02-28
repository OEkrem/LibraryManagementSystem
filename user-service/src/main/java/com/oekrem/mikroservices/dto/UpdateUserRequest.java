package com.oekrem.mikroservices.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record UpdateUserRequest (

        @NotBlank(message = "Username is required")
        String username,
        @NotBlank(message = "Password is required")
        String password,
        String firstName,
        String lastName,
        @NotBlank(message = "Email is required")
        String email,
        String phone

){
}
