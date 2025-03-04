package com.oekrem.mikroservices.controller;

import com.oekrem.mikroservices.dto.BookResponse;
import com.oekrem.mikroservices.dto.CreateBookRequest;
import com.oekrem.mikroservices.dto.UpdateBookRequest;
import com.oekrem.mikroservices.service.BookService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
                title = "Book API",
                version = "1.0",
                description = "Kitap işlemleri için API",
                contact = @Contact(name = "Ekrem", email = "oekremyildirim@outlook.com")
        )
)
@Tag(name = "Book API", description = "Kitaplarla ilgili işlemleri yönetir.")
@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    @Operation(summary = "Get All Books", description = "Returns a list of all books.")
    @ApiResponse(responseCode = "200", description = "Succesful", content = @Content(schema = @Schema(implementation = BookResponse.class)) )
    public ResponseEntity<Page<BookResponse>> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String filter
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllBooks(pageable, filter));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Book by id", description = "Path: id")
    @ApiResponse(responseCode = "200", description = "Succesful", content = @Content(schema = @Schema(implementation = BookResponse.class)) )
    public ResponseEntity<BookResponse> getBookById(
            @Parameter(description = "Book id", example = "12",required = true)
            @PathVariable Long id
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Create new Book", description = "Request: title, author, publisher, publishedDate, pages, language, description, category_id, stock, rating, edition")
    @ApiResponse(responseCode = "201", description = "Created succesfully", content = @Content(schema = @Schema(implementation = BookResponse.class)) )
    @ApiResponse(responseCode = "404", description = "Already exist")
    public ResponseEntity<BookResponse> createBook(@RequestBody @Valid CreateBookRequest createBookRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(createBookRequest));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update the Book", description = "Path: id, Request: title, author, publisher, publishedDate, pages, language, description, category_id, stock, rating, edition")
    @ApiResponse(responseCode = "200", description = "Succesful", content = @Content(schema = @Schema(implementation = BookResponse.class)) )
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @RequestBody @Valid UpdateBookRequest updateBookRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(id, updateBookRequest));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Patch the Book", description = "Path: id, Request: title, author, publisher, publishedDate, pages, language, description, category_id, stock, rating, edition")
    @ApiResponse(responseCode = "200", description = "Succesful", content = @Content(schema = @Schema(implementation = BookResponse.class)) )
    public ResponseEntity<BookResponse> patchBook(@PathVariable Long id, @RequestBody UpdateBookRequest updateBookRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.patchBook(id, updateBookRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete the Book", description = "Path: id")
    @ApiResponse(responseCode = "204", description = "Succesful")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
