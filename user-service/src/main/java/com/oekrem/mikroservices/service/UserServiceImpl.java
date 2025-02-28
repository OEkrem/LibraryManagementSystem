package com.oekrem.mikroservices.service;

import com.oekrem.mikroservices.dto.UserResponse;
import com.oekrem.mikroservices.model.User;
import com.oekrem.mikroservices.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public List<UserResponse> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map( p-> UserResponse.builder()
                        .id(p.getId())
                        .username(p.getUsername())
                        .password(p.getPassword())
                        .email(p.getEmail())
                        .firstName(p.getFirstName())
                        .lastName(p.getLastName())
                        .phone(p.getPhone())
                        .build())
                .collect(Collectors.toList());
    }
}
