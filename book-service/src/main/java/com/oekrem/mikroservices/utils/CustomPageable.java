package com.oekrem.mikroservices.utils;

import lombok.*;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class CustomPageable{

    private int pageNumber;
    private int pageSize;
    private long offset;
    private boolean hasPrevious;
    private boolean isPaged;

    private CustomPageable next;
    private CustomPageable previousOrFirst;
    private CustomPageable first;

    public static CustomPageable toCustomPageable(Pageable pageable, int processStep) {

        if (pageable == null) return null;
        CustomPageable customPageable = new CustomPageable();

        if (processStep == 0) customPageable.setFirst(toCustomPageable(pageable.first(), processStep + 1));
        if (processStep == 1) customPageable.setNext(toCustomPageable(pageable.next(), processStep + 1));
        if (processStep == 2) customPageable.setPreviousOrFirst(toCustomPageable(pageable.previousOrFirst(), processStep + 1));

        customPageable.setHasPrevious(pageable.hasPrevious());
        customPageable.setPageSize(pageable.getPageSize());
        customPageable.setPageNumber(pageable.getPageNumber());
        customPageable.setOffset(pageable.getOffset());

        return customPageable;
    }
}
