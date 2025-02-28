package com.oekrem.mikroservices.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UserResponse (

        UUID id,
        String username,
        String password,
        String firstName,
        String lastName,
        String email,
        String phone
){
}
