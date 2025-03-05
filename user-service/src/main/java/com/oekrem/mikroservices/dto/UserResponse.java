package com.oekrem.mikroservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "User Response Model")
public record UserResponse (

        @Schema(name = "id", example = "1")
        Long id,
        @Schema(name = "username", example = "username")
        String username,
        @Schema(name = "password", example = "password")
        String password,
        @Schema(name = "firstName", example = "first")
        String firstName,
        @Schema(name = "lastName", example = "last")
        String lastName,
        @Schema(name = "email", example = "email@example.com")
        String email,
        @Schema(name = "phone", example = "532 321 32 32")
        String phone
){
}
