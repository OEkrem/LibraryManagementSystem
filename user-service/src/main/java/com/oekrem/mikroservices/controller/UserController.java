package com.oekrem.mikroservices.controller;

import com.oekrem.mikroservices.dto.UserResponse;
import com.oekrem.mikroservices.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<UserResponse> getAllUsers() {
        return userService.findAllUsers();
    }
}
