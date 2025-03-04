package com.oekrem.mikroservices.service;

import com.oekrem.mikroservices.dto.BookResponse;
import com.oekrem.mikroservices.dto.CreateBookRequest;
import com.oekrem.mikroservices.dto.UpdateBookRequest;
import com.oekrem.mikroservices.exception.BookNotFoundException;
import com.oekrem.mikroservices.mapper.BookMapper;
import com.oekrem.mikroservices.model.Book;
import com.oekrem.mikroservices.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public Page<BookResponse> findAllBooks(Pageable pageable, String filter) {
        Page<Book> booksPage = null;
        if(filter != null && !filter.isEmpty())
            booksPage = bookRepository.findByNameContaining(pageable, filter);
        else
            booksPage = bookRepository.findAll(pageable);

        return  booksPage.map(bookMapper::toResponse);
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
