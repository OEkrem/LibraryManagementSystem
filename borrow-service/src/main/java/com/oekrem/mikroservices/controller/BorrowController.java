package com.oekrem.mikroservices.controller;

import com.oekrem.mikroservices.dto.BorrowResponse;
import com.oekrem.mikroservices.dto.CreateBorrowRequest;
import com.oekrem.mikroservices.dto.PatchBorrowRequest;
import com.oekrem.mikroservices.dto.UpdateBorrowRequest;
import com.oekrem.mikroservices.model.BorrowStatus;
import com.oekrem.mikroservices.service.BorrowService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@OpenAPIDefinition(
        info = @Info(
                title = "Borrow API",
                version = "1.0",
                description = "Ödünç alma işlemleri için API",
                contact = @Contact(name = "Ekrem", email = "oekremyildirim@outlook.com")
        )
)
@Tag(name = "Borrow API", description = "Ödünç alma işlemlerini yönetir.")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/borrows")
public class BorrowController {

    private final BorrowService borrowService;

    @GetMapping
    @Operation(summary = "Get All Borrows", description = "Parameters: int page, int size " +
            "\nBorrowStatus status(not required), Long user_id(not required), Long book_id(not required)" +
            "\nAllow us only choose one filter parameter. For example if you want to filter by book_id dont use other filters")
    @ApiResponse(responseCode = "200", description = "Get All Borrows Succesfully")
    public ResponseEntity<Page<BorrowResponse>> getAllBorrows(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) BorrowStatus status,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) Long bookId
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(borrowService.getAll(pageable, status, userId, bookId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get borrow by id", description = "Path Variable: Long id")
    @ApiResponse(responseCode = "200", description = "Borrow get by id Succesfully")
    @ApiResponse(responseCode = "404", description = "Borrow Not Found")
    public ResponseEntity<BorrowResponse> getBorrowsById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(borrowService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Create new Borrow", description = "RequestBody: CreateBorrowRequest")
    @ApiResponse(responseCode = "201", description = "Borrow Created Succesfully")
    @ApiResponse(responseCode = "400", description = "Bad Request, check create request parameters")
    public ResponseEntity<BorrowResponse> createBorrow(
            @RequestBody @Valid CreateBorrowRequest createBorrowRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowService.save(createBorrowRequest));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update Borrow", description = "Path: Long borrow_id, RequestBody: UpdateBorrowRequest")
    @ApiResponse(responseCode = "200", description = "Borrow Updated Succesfully")
    @ApiResponse(responseCode = "400", description = "Bad Request, check update request parameters")
    public ResponseEntity<BorrowResponse> updateBorrow(
            @PathVariable Long id,
            @RequestBody @Valid UpdateBorrowRequest updateBorrowRequest){
        return ResponseEntity.status(HttpStatus.OK).body(borrowService.update(id, updateBorrowRequest));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Patch Borrow", description = "Path: Long borrow_id, Request: PatchBorrowRequest")
    @ApiResponse(responseCode = "200", description = "Patch Borrow Succesfully")
    @ApiResponse(responseCode = "400", description = "Bad Request, check update request parameters")
    public ResponseEntity<BorrowResponse> patchBorrow(
            @PathVariable Long id,
            @RequestBody @Valid PatchBorrowRequest patchBorrowRequest
    ){
        return ResponseEntity.status(HttpStatus.OK).body(borrowService.patch(id, patchBorrowRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Borrow", description = "Path: Long borrow id")
    @ApiResponse(responseCode = "204", description = "Deleted succesfully")
    @ApiResponse(responseCode = "400", description = "Bad Request, check update request parameters")
    public ResponseEntity<Void> deleteBorrow(
            @PathVariable Long id
    ){
        borrowService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}