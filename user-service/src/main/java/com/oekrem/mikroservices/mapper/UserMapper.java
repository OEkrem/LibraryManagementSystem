package com.oekrem.mikroservices.mapper;

import com.oekrem.mikroservices.dto.CreateUserRequest;
import com.oekrem.mikroservices.dto.UpdateUserRequest;
import com.oekrem.mikroservices.dto.UserResponse;
import com.oekrem.mikroservices.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse toResponse(User user);
    User toUserFromCreateRequest(CreateUserRequest createUserRequest);
    User toUserFromUpdateRequest(UpdateUserRequest updateUserRequest);

}
