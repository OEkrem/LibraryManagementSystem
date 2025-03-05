package com.oekrem.mikroservices.mapper;

import com.oekrem.mikroservices.dto.BorrowResponse;
import com.oekrem.mikroservices.dto.CreateBorrowRequest;
import com.oekrem.mikroservices.dto.PatchBorrowRequest;
import com.oekrem.mikroservices.dto.UpdateBorrowRequest;
import com.oekrem.mikroservices.model.Borrow;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BorrowMapper {

    BorrowResponse toResponse(Borrow borrow);
    Borrow toBorrowFromCreateRequest(CreateBorrowRequest createBorrowRequest);
    Borrow toBorrowFromUpdateRequest(UpdateBorrowRequest updateBorrowRequest);

    @Mapping(target = "id", ignore = true)
    void patchBorrowFromRequest(PatchBorrowRequest patchBorrowRequest, @MappingTarget Borrow borrow);

}
