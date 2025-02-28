package com.oekrem.mikroservices.service;

import com.oekrem.mikroservices.dto.CreateUserRequest;
import com.oekrem.mikroservices.dto.UpdateUserRequest;
import com.oekrem.mikroservices.dto.UserResponse;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserResponse> findAll();
    UserResponse findById(UUID id);
    UserResponse save(CreateUserRequest createUserRequest);
    UserResponse update(UUID id, UpdateUserRequest updateUserRequest);
    void deleteById(UUID id);

    UserResponse patch(UUID id, UpdateUserRequest updateUserRequest);
}
