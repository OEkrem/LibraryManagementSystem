package com.oekrem.mikroservices.utils;

import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public final class CustomPage<T>{

        private List<T> content;
        private CustomPageable pageable;
        private long totalElements;
        private int totalPages;
        private boolean first;
        private boolean last;
        private boolean hasNext;
        private boolean hasPrevious;

        public static <T> CustomPage<T> toCustomPage(Page<T> page) {
            return CustomPage.<T>builder()
                    .first(page.isFirst())
                    .last(page.isLast())
                    .content(page.getContent())
                    .hasNext(page.hasNext())
                    .hasPrevious(page.hasPrevious())
                    .pageable(CustomPageable.toCustomPageable(page.getPageable(), 0))
                    .totalElements(page.getTotalElements())
                    .totalPages(page.getTotalPages())
                    .build();
        }

}
