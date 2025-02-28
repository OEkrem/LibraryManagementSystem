package com.oekrem.mikroservices.mapper;

import com.oekrem.mikroservices.dto.BookResponse;
import com.oekrem.mikroservices.dto.CreateBookRequest;
import com.oekrem.mikroservices.dto.UpdateBookRequest;
import com.oekrem.mikroservices.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookResponse toResponse(Book book);
    Book toBookFromCreateRequest(CreateBookRequest createBookRequest);
    Book toBookFromUpdateRequest(UpdateBookRequest updateBookRequest);

}
