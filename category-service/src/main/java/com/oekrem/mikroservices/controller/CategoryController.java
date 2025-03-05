package com.oekrem.mikroservices.controller;

import com.oekrem.mikroservices.dto.CategoryResponse;
import com.oekrem.mikroservices.dto.CreateCategoryRequest;
import com.oekrem.mikroservices.dto.PatchCategoryRequest;
import com.oekrem.mikroservices.dto.UpdateCategoryRequest;
import com.oekrem.mikroservices.service.CategoryService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@OpenAPIDefinition(info = @Info(
        title = "Category API",
        version = "1.0",
        description = "Describes categories of books",
        contact = @Contact(name = "Ekrem", email = "oekremyildirim@outlook.com")
))
@Tag(name = "Category API", description = "Describes categories of books")
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Get all Categories", description = "Parameters: int page, int size, String name")
    @ApiResponse(responseCode = "200", description = "Successful")
    public ResponseEntity<Page<CategoryResponse>> getAllCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findAll(page, size, name));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Category by ID", description = "Path Variable : Long id")
    @ApiResponse(responseCode = "200", description = "Successful")
    @ApiResponse(responseCode = "404", description = "Borrow Not Found")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Create Category", description = "RequestBody: CreateCategoryRequest Model")
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "400", description = "Bad Request, check create request parameters")
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryService.save(createCategoryRequest));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Category", description = "Path: Long id, Request: UpdateCategoryRequest Model")
    @ApiResponse(responseCode = "200", description = " Successful")
    @ApiResponse(responseCode = "400", description = "Bad Request, check update request parameters")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody UpdateCategoryRequest updateCategoryRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.update(id, updateCategoryRequest));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Patch Category", description = "Path: Long id, Request: PatchCategoryRequest Model")
    @ApiResponse(responseCode = "200", description = "Successful")
    @ApiResponse(responseCode = "400", description = "Bad Request, check update request parameters")
    public ResponseEntity<CategoryResponse> patchCategory(@PathVariable Long id, @RequestBody PatchCategoryRequest patchCategoryRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.patch(id, patchCategoryRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Patch Category", description = "Path: Long id")
    @ApiResponse(responseCode = "204", description = "Successful")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
