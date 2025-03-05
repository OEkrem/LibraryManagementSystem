package com.oekrem.mikroservices.service;


import com.oekrem.mikroservices.dto.BorrowResponse;
import com.oekrem.mikroservices.dto.CreateBorrowRequest;
import com.oekrem.mikroservices.dto.PatchBorrowRequest;
import com.oekrem.mikroservices.dto.UpdateBorrowRequest;
import com.oekrem.mikroservices.model.BorrowStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BorrowService {

     Page<BorrowResponse> getAll(Pageable pageable, BorrowStatus status, Long userId, Long bookId);
     BorrowResponse findById(Long id);
     BorrowResponse save(CreateBorrowRequest createBorrowRequest);
     BorrowResponse update(Long id, UpdateBorrowRequest updateBorrowRequest);
     BorrowResponse patch(Long id, PatchBorrowRequest patchBorrowRequest);
     void delete(Long id);
}
