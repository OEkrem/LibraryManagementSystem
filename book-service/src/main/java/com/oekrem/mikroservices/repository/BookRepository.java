package com.oekrem.mikroservices.repository;

import com.oekrem.mikroservices.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
