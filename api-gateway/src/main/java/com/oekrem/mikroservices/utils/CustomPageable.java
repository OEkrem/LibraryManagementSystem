package com.oekrem.mikroservices.utils;

import lombok.Builder;

@Builder
public record CustomPageable(
        int getPageNumber,
        int getPageSize,
        long getOffset,
        CustomPageable next,
        CustomPageable previousOrFirst,
        CustomPageable first,
        boolean hasPrevious
) {
    //Pageable withPage(int pageNumber)
}
