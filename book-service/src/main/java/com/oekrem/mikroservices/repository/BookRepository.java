package com.oekrem.mikroservices.repository;

import com.oekrem.mikroservices.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {

    Page<Book> findByTitleContaining(Pageable pageable, String filter);
    Page<Book> findAll(Pageable pageable);
    Optional<Book> findById(Long id);
    Book save(Book book);

    void deleteById(Long id);
    void deleteAll();
}
