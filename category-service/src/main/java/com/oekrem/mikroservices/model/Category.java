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
    @GeneratedValue(strategy = GenerationType.UUID)
    private Integer id;

    private String name;
    private String description;

}
