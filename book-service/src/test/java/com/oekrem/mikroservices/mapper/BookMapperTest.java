package com.oekrem.mikroservices.mapper;

import com.oekrem.mikroservices.dto.BookResponse;
import com.oekrem.mikroservices.dto.CreateBookRequest;
import com.oekrem.mikroservices.dto.UpdateBookRequest;
import com.oekrem.mikroservices.model.Book;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mapstruct.factory.Mappers;

public class BookMapperTest {

    private final BookMapper bookMapper = Mappers.getMapper(BookMapper.class);

    @Test
    public void testToResponse(){
        Book book = Book.builder()
                .id(1L)
                .title("title")
                .author("author")
                .pages(321)
                .language("english")
                .category_id(1L)
                .description("description")
                .edition("edition")
                .stock(12)
                .publishedYear(2015)
                .publisher("publisher")
                .rating(1.2F)
                .build();
        System.out.println(book);
        BookResponse bookResponse = bookMapper.toResponse(book);
        System.out.println(bookResponse);
        //Assert
        assertEquals(book.getAuthor(), bookResponse.author());
        assertEquals(book.getId(), bookResponse.id());
        assertEquals(book.getPages(), bookResponse.pages());
        assertEquals(book.getPublishedYear(), bookResponse.publishedYear());
    }

    @Test
    public void testToBookFromCreateRequest(){
        CreateBookRequest createBookRequest = CreateBookRequest.builder()
                .title("title")
                .author("author")
                .pages(321)
                .language("english")
                .category_id(1L)
                .description("description")
                .edition("edition")
                .stock(12)
                .publishedYear(2015)
                .publisher("publisher")
                .rating(1.2F)
                .build();
        Book book = bookMapper.toBookFromCreateRequest(createBookRequest);

        assertEquals(book.getTitle(), createBookRequest.title());
        assertEquals(book.getAuthor(), createBookRequest.author());
        assertEquals(book.getPages(), createBookRequest.pages());
        assertEquals(book.getLanguage(), createBookRequest.language());
        assertEquals(book.getCategory_id(), createBookRequest.category_id());
        assertEquals(book.getDescription(), createBookRequest.description());
        assertEquals(book.getEdition(), createBookRequest.edition());
        assertEquals(book.getStock(), createBookRequest.stock());
        assertEquals(book.getPublishedYear(), createBookRequest.publishedYear());
        assertEquals(book.getPublisher(), createBookRequest.publisher());
        assertEquals(book.getRating(), createBookRequest.rating());
    }

    @Test
    public void testToBookFromUpdateRequest(){
        UpdateBookRequest updateBookRequest = UpdateBookRequest.builder()
                .title("title")
                .author("author")
                .pages(321)
                .language("english")
                .category_id(1L)
                .description("description")
                .edition("edition")
                .stock(12)
                .publishedYear(2015)
                .publisher("publisher")
                .rating(1.2F)
                .build();
        Book book = bookMapper.toBookFromUpdateRequest(updateBookRequest);

        assertEquals(book.getTitle(), updateBookRequest.title());
        assertEquals(book.getAuthor(), updateBookRequest.author());
        assertEquals(book.getPages(), updateBookRequest.pages());
        assertEquals(book.getLanguage(), updateBookRequest.language());
        assertEquals(book.getCategory_id(), updateBookRequest.category_id());
        assertEquals(book.getDescription(), updateBookRequest.description());
        assertEquals(book.getEdition(), updateBookRequest.edition());
        assertEquals(book.getStock(), updateBookRequest.stock());
        assertEquals(book.getPublishedYear(), updateBookRequest.publishedYear());
        assertEquals(book.getPublisher(), updateBookRequest.publisher());
        assertEquals(book.getRating(), updateBookRequest.rating());
    }

    @Test
    public void testUpdateBookFromRequest(){
        Book book = Book.builder()
                .id(1L)
                .title("title")
                .author("author")
                .pages(321)
                .language("english")
                .category_id(1L)
                .description("description")
                .edition("edition")
                .stock(12)
                .publishedYear(2015)
                .publisher("publisher")
                .rating(1.2F)
                .build();

        UpdateBookRequest updateBookRequest = UpdateBookRequest.builder()
                .author("author2")
                .pages(320)
                .build();

        bookMapper.updateBookFromRequest(updateBookRequest, book);

        //Assert
        assertEquals("author2", book.getAuthor());
        assertEquals(320, book.getPages());
        assertEquals("description", book.getDescription());
    }

    @Test
    public void testUpdateBookFromRequestWithNullValues() {
        // Arrange
        Book book = new Book();
        book.setId(1L);
        book.setTitle("Original Title");
        book.setAuthor("Original Author");

        UpdateBookRequest request = UpdateBookRequest.builder()
                .author(null)
                .title(null)
                .build();

        bookMapper.updateBookFromRequest(request, book);

        // Assert
        assertEquals("Original Title", book.getTitle());
        assertEquals("Original Author", book.getAuthor());
    }
}
