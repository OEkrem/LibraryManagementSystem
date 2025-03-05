package com.oekrem.mikroservices.repository;

import com.oekrem.mikroservices.model.Borrow;
import com.oekrem.mikroservices.model.BorrowStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {

    Page<Borrow> findAll(Pageable pageable);

    @Query("SELECT b FROM Borrow b where b.bookId = :book_id")
    Page<Borrow> findByBookIdContaining (Pageable pageable, @Param("book_id") Long book_id);

    @Query("SELECT b FROM Borrow b where b.userId = :user_id")
    Page<Borrow> findByUserIdContaining (Pageable pageable, @Param("user_id") Long user_id);

    @Query("SELECT b FROM Borrow b WHERE b.status = :status")
    Page<Borrow> findByStatusContaining (Pageable pageable, @Param("status") BorrowStatus status);

    Optional<Borrow> findById (Long id);

    void delete(Borrow borrow);
    void deleteById(Long id);
    void deleteAll();

}
