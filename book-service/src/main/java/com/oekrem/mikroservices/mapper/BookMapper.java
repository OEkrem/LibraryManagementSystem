package com.oekrem.mikroservices.mapper;

import com.oekrem.mikroservices.dto.BookResponse;
import com.oekrem.mikroservices.dto.CreateBookRequest;
import com.oekrem.mikroservices.dto.UpdateBookRequest;
import com.oekrem.mikroservices.model.Book;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BookMapper {

    BookResponse toResponse(Book book);
    Book toBookFromCreateRequest(CreateBookRequest createBookRequest);
    Book toBookFromUpdateRequest(UpdateBookRequest updateBookRequest);

    @Mapping(target= "id",  ignore = true) // id alanını ignore edebiliriz dedik
    void updateBookFromRequest(UpdateBookRequest request, @MappingTarget Book book);
}
