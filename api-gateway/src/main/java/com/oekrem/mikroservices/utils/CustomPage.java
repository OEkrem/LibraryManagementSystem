package com.oekrem.mikroservices.utils;

import lombok.Builder;

import java.util.List;

@Builder
public record CustomPage<T>(
        List<T> content,
        CustomPageable pageable,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last,
        boolean hasNext,
        boolean hasPrevious
) {
}
