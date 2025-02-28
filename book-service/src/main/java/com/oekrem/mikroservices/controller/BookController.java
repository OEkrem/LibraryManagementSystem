package com.oekrem.mikroservices.controller;

import com.oekrem.mikroservices.dto.BookResponse;
import com.oekrem.mikroservices.dto.CreateBookRequest;
import com.oekrem.mikroservices.dto.UpdateBookRequest;
import com.oekrem.mikroservices.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAllBooks());
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findById(id));
    }

    @PostMapping("/books")
    public ResponseEntity<BookResponse> createBook(@RequestBody @Valid CreateBookRequest createBookRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(createBookRequest));
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable UUID id, @RequestBody @Valid UpdateBookRequest updateBookRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(id, updateBookRequest));
    }

    @PatchMapping("/books/{id}")
    public ResponseEntity<BookResponse> patchBook(@PathVariable UUID id, @RequestBody UpdateBookRequest updateBookRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(bookService.patchBook(id, updateBookRequest));
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
