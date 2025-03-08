package com.oekrem.mikroservices.service;

import com.oekrem.mikroservices.dto.BookResponse;
import com.oekrem.mikroservices.dto.CreateBookRequest;
import com.oekrem.mikroservices.dto.UpdateBookRequest;
import com.oekrem.mikroservices.exception.BookNotFoundException;
import com.oekrem.mikroservices.mapper.BookMapper;
import com.oekrem.mikroservices.model.Book;
import com.oekrem.mikroservices.model.BookStatus;
import com.oekrem.mikroservices.repository.BookRepository;
import com.oekrem.mikroservices.utils.CustomPage;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public CustomPage<BookResponse> findAllBooks(int page, int size, String filter, BookStatus status) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> booksPage = null;
        if(filter != null && !filter.isEmpty() && status != null)
            booksPage = bookRepository.findByTitleAndStatusContaining(pageable, filter, status);
        else if(status != null)
            booksPage = bookRepository.findByStatusContaining(pageable, status);
        else if(filter != null && !filter.isEmpty())
            booksPage = bookRepository.findByTitleContaining(pageable, filter);
        else
            booksPage = bookRepository.findAll(pageable);

        Page<BookResponse> booksResponse = booksPage.map(bookMapper::toResponse);
        return CustomPage.toCustomPage(booksResponse);
    }

    @Override
    public BookResponse findById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow( () -> new BookNotFoundException("Book with id " + id + " not found"));
        return bookMapper.toResponse(book);
    }

    @Override
    @Transactional
    public BookResponse saveBook(CreateBookRequest createBookRequest) {
        Book book = bookMapper.toBookFromCreateRequest(createBookRequest);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toResponse(savedBook);
    }

    @Override
    @Transactional
    public BookResponse updateBook(Long id, UpdateBookRequest updateBookRequest) {
        Book book = bookMapper.toBookFromUpdateRequest(updateBookRequest);
        book.setId(id);
        Book updatedBook = bookRepository.save(book);
        return bookMapper.toResponse(updatedBook);
    }

    @Override
    @Transactional
    public void deleteBook(Long id) {
        bookRepository.findById(id)
                .orElseThrow( () -> new BookNotFoundException("Book with id " + id + " not found"));
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public BookResponse patchBook(Long id, UpdateBookRequest updateBookRequest) {
        Book book = bookRepository.findById(id)
                .orElseThrow( ()-> new BookNotFoundException("Book with id " + id + " not found"));

        bookMapper.updateBookFromRequest(updateBookRequest, book);

        Book updatedBook = bookRepository.save(book);
        return bookMapper.toResponse(updatedBook);
    }

}
