package com.oekrem.mikroservices.repository;

import com.oekrem.mikroservices.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Page<Category> findByNameContaining(Pageable pageable, String categoryName);
    Page<Category> findAll(Pageable pageable);

}
