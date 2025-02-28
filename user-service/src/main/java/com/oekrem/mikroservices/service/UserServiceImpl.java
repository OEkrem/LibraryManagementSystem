package com.oekrem.mikroservices.service;

import com.oekrem.mikroservices.dto.CreateUserRequest;
import com.oekrem.mikroservices.dto.UpdateUserRequest;
import com.oekrem.mikroservices.dto.UserResponse;
import com.oekrem.mikroservices.exception.UserNotFoundException;
import com.oekrem.mikroservices.mapper.UserMapper;
import com.oekrem.mikroservices.model.User;
import com.oekrem.mikroservices.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponse> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse findById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse save(CreateUserRequest createUserRequest){
        User user = userMapper.toUserFromCreateRequest(createUserRequest);
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public UserResponse update(UUID id, UpdateUserRequest updateUserRequest) {
        User user = userMapper.toUserFromUpdateRequest(updateUserRequest);
        user.setId(id);
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.findById(id)
                .orElseThrow( () -> new UserNotFoundException("User not found with id: " + id));
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse patch(UUID id, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(id)
                .orElseThrow( () -> new UserNotFoundException("User not found with id: " + id));

        if(updateUserRequest.email() != null)
            user.setEmail(updateUserRequest.email());
        if(updateUserRequest.password() != null)
            user.setPassword(updateUserRequest.password());
        if(updateUserRequest.username() != null)
            user.setUsername(updateUserRequest.username());
        if(updateUserRequest.firstName() != null)
            user.setFirstName(updateUserRequest.firstName());
        if(updateUserRequest.lastName() != null)
            user.setLastName(updateUserRequest.lastName());
        if(updateUserRequest.phone() != null)
            user.setPhone(updateUserRequest.phone());

        User updatedUser = userRepository.save(user);
        return userMapper.toResponse(updatedUser);
    }

}
