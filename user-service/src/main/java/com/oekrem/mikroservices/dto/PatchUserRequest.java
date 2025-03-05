package com.oekrem.mikroservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "Patch User Request Model")
public record PatchUserRequest (

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
