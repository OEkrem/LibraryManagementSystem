package com.oekrem.mikroservices.service;

import com.oekrem.mikroservices.dto.CreateUserRequest;
import com.oekrem.mikroservices.dto.PatchUserRequest;
import com.oekrem.mikroservices.dto.UpdateUserRequest;
import com.oekrem.mikroservices.dto.UserResponse;
import org.springframework.data.domain.Page;

public interface UserService {

    Page<UserResponse> findAll(int page, int size, String email);
    UserResponse findById(Long id);
    UserResponse save(CreateUserRequest createUserRequest);
    UserResponse update(Long id, UpdateUserRequest updateUserRequest);
    void deleteById(Long id);

    UserResponse patch(Long id, PatchUserRequest patchUserRequest);
}
