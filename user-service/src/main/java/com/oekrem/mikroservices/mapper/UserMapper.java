package com.oekrem.mikroservices.mapper;

import com.oekrem.mikroservices.dto.CreateUserRequest;
import com.oekrem.mikroservices.dto.PatchUserRequest;
import com.oekrem.mikroservices.dto.UpdateUserRequest;
import com.oekrem.mikroservices.dto.UserResponse;
import com.oekrem.mikroservices.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserResponse toResponse(User user);
    User toUserFromCreateRequest(CreateUserRequest createUserRequest);
    User toUserFromUpdateRequest(UpdateUserRequest updateUserRequest);

    @Mapping(target = "id", ignore = true)
    void patchUserFromRequest(PatchUserRequest patchUserRequest, @MappingTarget User user);

}
