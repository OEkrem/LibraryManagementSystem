package com.oekrem.mikroservices.service;

import com.oekrem.mikroservices.dto.BookResponse;
import com.oekrem.mikroservices.dto.CreateBookRequest;
import com.oekrem.mikroservices.dto.UpdateBookRequest;
import com.oekrem.mikroservices.model.BookStatus;
import org.springframework.data.domain.Page;

public interface BookService {

    Page<BookResponse> findAllBooks(int page, int size, String filter, BookStatus status);
    BookResponse findById(Long id);
    BookResponse saveBook(CreateBookRequest createBookRequest);
    BookResponse updateBook(Long id, UpdateBookRequest updateBookRequest);
    void deleteBook(Long id);

    BookResponse patchBook(Long id, UpdateBookRequest updateBookRequest);

}
