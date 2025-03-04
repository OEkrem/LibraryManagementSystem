package com.oekrem.mikroservices.repository;

import com.oekrem.mikroservices.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findByEmail(Pageable pageable, String email);
    Page<User> findAll(Pageable pageable);
}
