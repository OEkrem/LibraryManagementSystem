package com.oekrem.mikroservices.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "borrow_t")
public class Borrow {

    @Id
    @SequenceGenerator(name = "borrow_seq_table", allocationSize = 1, initialValue = 1, sequenceName = "borrow_seq_table")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "borrow_seq_table")
    private Long id;

    private Long userId;

    private Long bookId;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime borrow_date;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime return_date;

    @Enumerated(EnumType.STRING)
    private BorrowStatus status;

    @PrePersist
    protected void onCreate() {
        borrow_date = LocalDateTime.now();
        return_date = borrow_date.plusDays(20);
        status = BorrowStatus.BORROWED;
    }

}
