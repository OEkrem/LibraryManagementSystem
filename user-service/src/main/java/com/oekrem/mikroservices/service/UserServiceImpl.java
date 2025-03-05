package com.oekrem.mikroservices.service;

import com.oekrem.mikroservices.dto.CreateUserRequest;
import com.oekrem.mikroservices.dto.PatchUserRequest;
import com.oekrem.mikroservices.dto.UpdateUserRequest;
import com.oekrem.mikroservices.dto.UserResponse;
import com.oekrem.mikroservices.exception.UserNotFoundException;
import com.oekrem.mikroservices.mapper.UserMapper;
import com.oekrem.mikroservices.model.User;
import com.oekrem.mikroservices.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Page<UserResponse> findAll(int page, int size, String email) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> users;
        if (email != null)
            users = userRepository.findByEmail(pageable, email);
        else
            users = userRepository.findAll(pageable);

        return users.map(userMapper::toResponse);
    }

    @Override
    public UserResponse findById(Long id) {
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
    public UserResponse update(Long id, UpdateUserRequest updateUserRequest) {
        User user = userMapper.toUserFromUpdateRequest(updateUserRequest);
        user.setId(id);
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.findById(id)
                .orElseThrow( () -> new UserNotFoundException("User not found with id: " + id));
        userRepository.deleteById(id);
    }

    @Override
    public UserResponse patch(Long id, PatchUserRequest patchUserRequest) {
        User user = userRepository.findById(id)
                .orElseThrow( () -> new UserNotFoundException("User not found with id: " + id));

        userMapper.patchUserFromRequest(patchUserRequest, user);

        User updatedUser = userRepository.save(user);
        return userMapper.toResponse(updatedUser);
    }

}
