package com.oekrem.mikroservices.service;

import com.oekrem.mikroservices.dto.BookResponse;
import com.oekrem.mikroservices.dto.CreateBookRequest;
import com.oekrem.mikroservices.dto.UpdateBookRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    Page<BookResponse> findAllBooks(Pageable pageable, String filter);
    BookResponse findById(Long id);
    BookResponse saveBook(CreateBookRequest createBookRequest);
    BookResponse updateBook(Long id, UpdateBookRequest updateBookRequest);
    void deleteBook(Long id);

    BookResponse patchBook(Long id, UpdateBookRequest updateBookRequest);

}
