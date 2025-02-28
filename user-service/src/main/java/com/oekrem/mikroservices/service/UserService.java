package com.oekrem.mikroservices.service;

import com.oekrem.mikroservices.dto.UserResponse;

import java.util.List;

public interface UserService {

    List<UserResponse> findAllUsers();
}
