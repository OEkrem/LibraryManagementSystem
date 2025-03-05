package com.oekrem.mikroservices.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories_t")
public class Category {

    @Id
    @SequenceGenerator(name = "category_seq_table", sequenceName = "category_seq_table", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq_table")
    private Long id;

    private String name;
    private String description;

}
