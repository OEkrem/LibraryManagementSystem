package com.oekrem.mikroservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.UUID;

@Builder
@Schema(description = "Book Response Model")
public record BookResponse (

        UUID id,
        @Schema(description = "Title of book", example = "Le Petit Prince")
        String title,
        @Schema(description = "Author of book", example = "Jules Verne")
        String author,
        @Schema(description = "Publisher of book", example = "Penguin Random House")
        String publisher,
        @Schema(description = "PublishedDate of book", example = "2017")
        Integer publishedDate,

        @Schema(description = "Pages of book", example = "312")
        String pages,
        @Schema(description = "Language of book", example = "English")
        String language,
        @Schema(description = "Description of book", example = "Antoine de Saint-Exupéry'nin kaleme aldığı bu klasik eser, bir pilotun çöle düşmesiyle tanıştığı gizemli ve bilge bir çocuğun, Küçük Prens’in hikâyesini anlatır.")
        String description,

        @Schema(description = "Category_id of book", example = "ecbf0aef-3071-47af-af2b-c7dc1dcaf1b7")
        UUID category_id,
        @Schema(description = "Stock of book", example = "12")
        Integer stock,
        @Schema(description = "Rating of book", example = "7.2")
        Float rating,
        @Schema(description = "Edition of book", example = "1.2")
        String edition
){
}
