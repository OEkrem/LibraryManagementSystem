package com.oekrem.mikroservices.service;

import com.oekrem.mikroservices.dto.BookResponse;
import com.oekrem.mikroservices.dto.CreateBookRequest;
import com.oekrem.mikroservices.dto.UpdateBookRequest;

import java.util.List;
import java.util.UUID;

public interface BookService {

    List<BookResponse> findAllBooks();
    BookResponse findById(UUID id);
    BookResponse saveBook(CreateBookRequest createBookRequest);
    BookResponse updateBook(UUID id, UpdateBookRequest updateBookRequest);
    void deleteBook(UUID id);

    BookResponse patchBook(UUID id, UpdateBookRequest updateBookRequest);

}
