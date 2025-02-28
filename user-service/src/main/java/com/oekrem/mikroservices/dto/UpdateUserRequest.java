package com.oekrem.mikroservices.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {

    @NotNull
    private UUID id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

}
