package com.oekrem.mikroservices.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Table(name = "books_t")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @SequenceGenerator(name = "book_seq_table", initialValue = 1, allocationSize = 1, sequenceName = "book_seq_table")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_seq_table")
    private Long id;

    private String title;
    private String author;
    private String publisher;
    private Integer publishedYear;

    private int pages;
    private String language;
    private String description;

    private Long category_id;

    private Integer stock;
    private Float rating;
    private String edition;

}
