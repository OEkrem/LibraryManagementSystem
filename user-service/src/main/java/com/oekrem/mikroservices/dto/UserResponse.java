package com.oekrem.mikroservices.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private UUID id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

}
