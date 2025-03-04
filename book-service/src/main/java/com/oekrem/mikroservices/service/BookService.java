package com.oekrem.mikroservices.service;

import com.oekrem.mikroservices.dto.BookResponse;
import com.oekrem.mikroservices.dto.CreateBookRequest;
import com.oekrem.mikroservices.dto.UpdateBookRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface BookService {

    Page<BookResponse> findAllBooks(Pageable pageable, String filter);
    BookResponse findById(UUID id);
    BookResponse saveBook(CreateBookRequest createBookRequest);
    BookResponse updateBook(UUID id, UpdateBookRequest updateBookRequest);
    void deleteBook(UUID id);

    BookResponse patchBook(UUID id, UpdateBookRequest updateBookRequest);

}
