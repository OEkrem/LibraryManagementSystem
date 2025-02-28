package com.oekrem.mikroservices.mapper;

import com.oekrem.mikroservices.dto.BookResponse;
import com.oekrem.mikroservices.dto.CreateBookRequest;
import com.oekrem.mikroservices.dto.UpdateBookRequest;
import com.oekrem.mikroservices.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookResponse toResponse(Book book);
    Book toBookFromCreateRequest(CreateBookRequest createBookRequest);
    Book toBookFromUpdateRequest(UpdateBookRequest updateBookRequest);
}
