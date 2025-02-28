package com.oekrem.mikroservices.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@Table(name = "book_t")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String title;
    private String author;
    private String publisher;
    private Integer publishedYear;

    private String pages;
    private String language;
    private String description;

    private UUID category_id;

    private Integer stock;
    private Float rating;
    private String edition;

}
