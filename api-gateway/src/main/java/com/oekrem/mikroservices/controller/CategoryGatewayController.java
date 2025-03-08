package com.oekrem.mikroservices.controller;

import com.oekrem.mikroservices.dto.categoryDto.CategoryResponse;
import com.oekrem.mikroservices.dto.categoryDto.CreateCategoryRequest;
import com.oekrem.mikroservices.dto.categoryDto.PatchCategoryRequest;
import com.oekrem.mikroservices.dto.categoryDto.UpdateCategoryRequest;
import com.oekrem.mikroservices.utils.CustomPage;
import com.oekrem.mikroservices.utils.ServiceUriResolver;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@OpenAPIDefinition(info = @Info(
        title = "API Gateway - Category API",
        version = "1.0",
        description = "Category API for Library System",
        contact = @Contact(name = "Ekrem", email = "oekremyildirim@outlook.com")
))
@Tag(name = "Gateway API - Category API", description = "Allows communication with category-service")

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryGatewayController {

    private final WebClient.Builder webClientBuilder;
    private final ServiceUriResolver serviceUriResolver;

    @PostConstruct
    public void init() {
        webClientBuilder.baseUrl(serviceUriResolver.getServiceUri("category-service", CategoryGatewayController.class));
    }

    @GetMapping
    @Operation(summary = "Get all Categories", description = "Parameters: int page, int size, String name")
    @ApiResponse(responseCode = "200", description = "Successful")
    public Mono<CustomPage<CategoryResponse>> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name
    ) {
        return webClientBuilder.build()
                .get()
                .uri( uriBuilder -> uriBuilder
                        .queryParam("page", page)
                        .queryParam("size", size)
                        .queryParamIfPresent("name", Optional.ofNullable(name))
                        .build()
                )
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<CustomPage<CategoryResponse>>() {});
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Category by ID", description = "Path Variable : Long id")
    @ApiResponse(responseCode = "200", description = "Successful")
    @ApiResponse(responseCode = "404", description = "Borrow Not Found")
    public Mono<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return webClientBuilder.build()
                .get()
                .uri( uriBuilder -> uriBuilder
                        .path("/{id}").build(id)
                )
                .retrieve()
                .bodyToMono(CategoryResponse.class);
    }

    @PostMapping
    @Operation(summary = "Create Category", description = "RequestBody: CreateCategoryRequest Model")
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "400", description = "Bad Request, check create request parameters")
    public Mono<CategoryResponse> saveCategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
        return webClientBuilder.build()
                .post()
                .bodyValue(createCategoryRequest)
                .retrieve()
                .bodyToMono(CategoryResponse.class);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Category", description = "Path: Long id, Request: UpdateCategoryRequest Model")
    @ApiResponse(responseCode = "200", description = " Successful")
    @ApiResponse(responseCode = "400", description = "Bad Request, check update request parameters")
    public Mono<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return webClientBuilder.build()
                .put()
                .uri( uriBuilder -> uriBuilder
                        .path("/{id}").build(id)
                )
                .bodyValue(updateCategoryRequest)
                .retrieve()
                .bodyToMono(CategoryResponse.class);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Patch Category", description = "Path: Long id, Request: PatchCategoryRequest Model")
    @ApiResponse(responseCode = "200", description = "Successful")
    @ApiResponse(responseCode = "400", description = "Bad Request, check update request parameters")
    public Mono<CategoryResponse> patchCategory(@PathVariable Long id, @RequestBody PatchCategoryRequest patchCategoryRequest) {
        return webClientBuilder.build()
                .patch()
                .uri( uriBuilder -> uriBuilder
                        .path("/{id}").build(id)
                )
                .bodyValue(patchCategoryRequest)
                .retrieve()
                .bodyToMono(CategoryResponse.class);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Patch Category", description = "Path: Long id")
    @ApiResponse(responseCode = "204", description = "Successful")
    public Mono<Void> deleteCategory(@PathVariable Long id) {
        return webClientBuilder.build()
                .delete()
                .uri( uriBuilder -> uriBuilder
                        .path("/{id}").build(id)
                )
                .retrieve()
                .bodyToMono(Void.class);
    }

}
