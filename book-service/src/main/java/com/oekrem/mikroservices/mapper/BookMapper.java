package com.oekrem.mikroservices.mapper;

import com.oekrem.mikroservices.dto.BookResponse;
import com.oekrem.mikroservices.dto.CreateBookRequest;
import com.oekrem.mikroservices.dto.UpdateBookRequest;
import com.oekrem.mikroservices.model.Book;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
//@Component
public interface BookMapper {

    BookResponse toResponse(Book book);
    Book toBookFromCreateRequest(CreateBookRequest createBookRequest);
    Book toBookFromUpdateRequest(UpdateBookRequest updateBookRequest);

    // Patch işlemleri için...
    //@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    //void updateBookFromRequest(UpdateBookRequest request, @MappingTarget Book book);
}
