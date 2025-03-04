package com.oekrem.mikroservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
@Schema(description = "Create Book Request Model")
public record CreateBookRequest (

        @NotBlank(message = "Title is required")
        @Schema(description = "Title of book", example = "Le Petit Prince")
        String title,
        @NotBlank(message = "Author is required")
        @Schema(description = "Author of book", example = "Jules Verne")
        String author,
        @NotBlank(message = "Publisher is required")
        @Schema(description = "Publisher of book", example = "Penguin Random House")
        String publisher,
        @NotNull(message = "Published Year is required")
        @Schema(description = "PublishedDate of book", example = "2017")
        Integer publishedYear,

        @Schema(description = "Pages of book", example = "312")
        int pages,
        @Schema(description = "Language of book", example = "English")
        String language,
        @NotBlank(message = "Description is required")
        @Schema(description = "Description of book", example = "Antoine de Saint-Exupéry'nin kaleme aldığı bu klasik eser, bir pilotun çöle düşmesiyle tanıştığı gizemli ve bilge bir çocuğun, Küçük Prens’in hikâyesini anlatır.")
        String description,

        @NotNull(message = "Category is required")
        @Schema(description = "Category_id of book", example = "1")
        Long category_id,
        @Schema(description = "Stock of book", example = "12")
        Integer stock,
        @Schema(description = "Rating of book", example = "7.2")
        Float rating,
        @Schema(description = "Edition of book", example = "1.2")
        String edition

){
}
