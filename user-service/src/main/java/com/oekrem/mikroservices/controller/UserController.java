package com.oekrem.mikroservices.controller;

import com.oekrem.mikroservices.dto.CreateUserRequest;
import com.oekrem.mikroservices.dto.PatchUserRequest;
import com.oekrem.mikroservices.dto.UpdateUserRequest;
import com.oekrem.mikroservices.dto.UserResponse;
import com.oekrem.mikroservices.service.UserService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@OpenAPIDefinition(info = @Info(
        title = "User API",
        version = "1.0",
        description = "User API for Library System",
        contact = @Contact(name = "Ekrem", email = "oekremyildirim@outlook.com")
))
@Tag(name = "Users API", description = "Allows us user management to Library System")
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Get All Users", description = "Parameters: int page, int size, String email( Not Required )")
    @ApiResponse(responseCode = "200", description = "Get All Users Succesfully")
    public ResponseEntity<Page<UserResponse>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String email
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll(page, size, email));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get User by id", description = "Path Variable: Long id")
    @ApiResponse(responseCode = "200", description = "User get by id Succesfully")
    @ApiResponse(responseCode = "404", description = "User Not Found")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Create new User", description = "RequestBody: CreateUserRequest")
    @ApiResponse(responseCode = "201", description = "User Created Succesfully")
    @ApiResponse(responseCode = "400", description = "Bad Request, check create request parameters")
    public ResponseEntity<UserResponse> saveUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(createUserRequest));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update User", description = "Path: Long user_id, RequestBody: UpdateUserRequest")
    @ApiResponse(responseCode = "200", description = "User Updated Succesfully")
    @ApiResponse(responseCode = "400", description = "Bad Request, check update request parameters")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody @Valid UpdateUserRequest updateUserRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, updateUserRequest));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Patch User", description = "Path: Long user_id, Request: PatchUserRequest")
    @ApiResponse(responseCode = "200", description = "User Patched Succesfully")
    @ApiResponse(responseCode = "400", description = "Bad Request, check update request parameters")
    public ResponseEntity<UserResponse> patchUser(@PathVariable Long id, @RequestBody @Valid PatchUserRequest patchUserRequest){
        return ResponseEntity.status(HttpStatus.OK).body(userService.patch(id, patchUserRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete User", description = "Path: Long user_id")
    @ApiResponse(responseCode = "204", description = "Deleted succesfully")
    @ApiResponse(responseCode = "400", description = "Bad Request, check update request parameters")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
