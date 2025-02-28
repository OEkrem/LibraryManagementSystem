package com.oekrem.mikroservices.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String firstName;
    private String lastName;
    @NotBlank
    private String email;
    private String phone;

}
