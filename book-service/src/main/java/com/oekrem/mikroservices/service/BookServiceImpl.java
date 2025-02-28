package com.oekrem.mikroservices.service;

import com.oekrem.mikroservices.dto.BookResponse;
import com.oekrem.mikroservices.dto.CreateBookRequest;
import com.oekrem.mikroservices.dto.UpdateBookRequest;
import com.oekrem.mikroservices.exception.BookNotFoundException;
import com.oekrem.mikroservices.mapper.BookMapper;
import com.oekrem.mikroservices.model.Book;
import com.oekrem.mikroservices.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public List<BookResponse> findAllBooks() {
        List<Book> books = bookRepository.findAll();
        return  books.stream()
                .map(bookMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponse findById(UUID id) {
        Book book = bookRepository.findById(id)
                .orElseThrow( () -> new BookNotFoundException("Book with id " + id + " not found"));
        return bookMapper.toResponse(book);
    }

    @Override
    public BookResponse saveBook(CreateBookRequest createBookRequest) {
        Book book = bookMapper.toBookFromCreateRequest(createBookRequest);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toResponse(savedBook);
    }

    @Override
    public BookResponse updateBook(UUID id, UpdateBookRequest updateBookRequest) {
        Book book = bookMapper.toBookFromUpdateRequest(updateBookRequest);
        book.setId(id);
        Book updatedBook = bookRepository.save(book);
        return bookMapper.toResponse(updatedBook);
    }

    @Override
    public void deleteBook(UUID id) {
        bookRepository.findById(id)
                .orElseThrow( () -> new BookNotFoundException("Book with id " + id + " not found"));
        bookRepository.deleteById(id);
    }

    @Override
    public BookResponse patchBook(UUID id, UpdateBookRequest updateBookRequest) {
        Book book = bookRepository.findById(id)
                .orElseThrow( ()-> new BookNotFoundException("Book with id " + id + " not found"));

        if(updateBookRequest.title() != null)
            book.setTitle(updateBookRequest.title());
        if(updateBookRequest.author() != null)
            book.setAuthor(updateBookRequest.author());
        if(updateBookRequest.publisher() != null)
            book.setPublisher(updateBookRequest.publisher());
        if(updateBookRequest.publishedDate() != null)
            book.setPublishedYear(updateBookRequest.publishedDate());

        if(updateBookRequest.pages() != null)
            book.setPages(updateBookRequest.pages());
        if(updateBookRequest.language() != null)
            book.setLanguage(updateBookRequest.language());
        if(updateBookRequest.description() != null)
            book.setDescription(updateBookRequest.description());

        if(updateBookRequest.category_id() != null)
            book.setCategory_id(updateBookRequest.category_id());
        if(updateBookRequest.stock() != null)
            book.setStock(updateBookRequest.stock());
        if(updateBookRequest.rating() != null)
            book.setRating(updateBookRequest.rating());
        if(updateBookRequest.edition() != null)
            book.setEdition(updateBookRequest.edition());

        Book updatedBook = bookRepository.save(book);
        return bookMapper.toResponse(updatedBook);
    }
}
