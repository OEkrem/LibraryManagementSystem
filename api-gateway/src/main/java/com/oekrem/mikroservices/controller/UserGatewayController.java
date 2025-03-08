package com.oekrem.mikroservices.controller;

import com.oekrem.mikroservices.dto.userDto.CreateUserRequest;
import com.oekrem.mikroservices.dto.userDto.PatchUserRequest;
import com.oekrem.mikroservices.dto.userDto.UpdateUserRequest;
import com.oekrem.mikroservices.dto.userDto.UserResponse;
import com.oekrem.mikroservices.utils.CustomPage;
import com.oekrem.mikroservices.utils.ServiceUriResolver;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@OpenAPIDefinition(info = @Info(
        title = "API Gateway - User API",
        version = "1.0",
        description = "User API for Library System",
        contact = @Contact(name = "Ekrem", email = "oekremyildirim@outlook.com")
))
@Tag(name = "Gateway API - Users Service", description = "Allows communication with user-service")

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserGatewayController {

    private final WebClient.Builder webClientBuilder;
    private final ServiceUriResolver serviceUriResolver;

    @PostConstruct
    public void init() {
        webClientBuilder.baseUrl(serviceUriResolver.getServiceUri("user-service", UserGatewayController.class));
    }

    @GetMapping
    @Operation(summary = "Get All Users", description = "Parameters: int page, int size, String email( Not Required )")
    @ApiResponse(responseCode = "200", description = "Get All Users Succesfully")
    public Mono<CustomPage<UserResponse>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String email
    ){
        return webClientBuilder.build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .queryParamIfPresent("email", Optional.ofNullable(email))
                        .build()
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CustomPage<UserResponse>>() {});
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get User by id", description = "Path Variable: Long id")
    @ApiResponse(responseCode = "200", description = "User get by id Succesfully")
    @ApiResponse(responseCode = "404", description = "User Not Found")
    public Mono<UserResponse> getUserById(@PathVariable Long id){
        return webClientBuilder.build()
                .get()
                .uri( uriBuilder -> uriBuilder
                        .path("/{id}").build(id)
                )
                .retrieve()
                .bodyToMono( UserResponse.class );
    }

    @PostMapping
    @Operation(summary = "Create new User", description = "RequestBody: CreateUserRequest")
    @ApiResponse(responseCode = "201", description = "User Created Succesfully")
    @ApiResponse(responseCode = "400", description = "Bad Request, check create request parameters")
    public Mono<UserResponse> createUser(@RequestBody @Valid CreateUserRequest createUserRequest){
        return webClientBuilder.build()
                .post()
                .bodyValue(createUserRequest)
                .retrieve()
                .bodyToMono(UserResponse.class);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update User", description = "Path: Long user_id, RequestBody: UpdateUserRequest")
    @ApiResponse(responseCode = "200", description = "User Updated Succesfully")
    @ApiResponse(responseCode = "400", description = "Bad Request, check update request parameters")
    public Mono<UserResponse> updateUser(@PathVariable Long id, @RequestBody @Valid UpdateUserRequest updateUserRequest) {
        return webClientBuilder.build()
                .put()
                .uri( uriBuilder -> uriBuilder
                        .path("/{id}").build(id)
                )
                .bodyValue(updateUserRequest)
                .retrieve()
                .bodyToMono(UserResponse.class);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Patch User", description = "Path: Long user_id, Request: PatchUserRequest")
    @ApiResponse(responseCode = "200", description = "User Patched Succesfully")
    @ApiResponse(responseCode = "400", description = "Bad Request, check update request parameters")
    public Mono<UserResponse> patchUser(@PathVariable Long id, @RequestBody @Valid PatchUserRequest patchUserRequest){
        return webClientBuilder.build()
                .patch()
                .uri( uriBuilder -> uriBuilder
                        .path("/{id}").build(id)
                )
                .bodyValue(patchUserRequest)
                .retrieve()
                .bodyToMono(UserResponse.class);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete User", description = "Path: Long user_id")
    @ApiResponse(responseCode = "204", description = "Deleted succesfully")
    @ApiResponse(responseCode = "400", description = "Bad Request, check update request parameters")
    public Mono<Void> deleteUser(@PathVariable Long id) {
        return webClientBuilder.build()
                .delete()
                .uri(uriBuilder -> uriBuilder
                        .path("/{id}").build(id)
                )
                .retrieve()
                .bodyToMono(Void.class);
    }

}
