package com.oekrem.mikroservices.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record BookResponse (

        UUID id,
        String title,
        String author,
        String publisher,
        Integer publishedDate,

        String pages,
        String language,
        String description,

        UUID category_id,
        Integer stock,
        Float rating,
        String edition
){
}
