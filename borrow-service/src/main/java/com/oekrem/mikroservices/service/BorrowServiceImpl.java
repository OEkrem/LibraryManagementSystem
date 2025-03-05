package com.oekrem.mikroservices.service;

import com.oekrem.mikroservices.dto.BorrowResponse;
import com.oekrem.mikroservices.dto.CreateBorrowRequest;
import com.oekrem.mikroservices.dto.PatchBorrowRequest;
import com.oekrem.mikroservices.dto.UpdateBorrowRequest;
import com.oekrem.mikroservices.exception.BorrowNotFoundException;
import com.oekrem.mikroservices.mapper.BorrowMapper;
import com.oekrem.mikroservices.model.Borrow;
import com.oekrem.mikroservices.model.BorrowStatus;
import com.oekrem.mikroservices.repository.BorrowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BorrowServiceImpl implements BorrowService {

    private final BorrowRepository borrowRepository;
    private final BorrowMapper borrowMapper;

    @Override
    public Page<BorrowResponse> getAll(Pageable pageable, BorrowStatus status, Long userId, Long bookId) {

        System.out.println("Status: " + status + " userId: " + userId + " bookId: " + bookId);
        Page<Borrow> borrows;

        if(status != null) {
            borrows = borrowRepository.findByStatusContaining(pageable, status);
        }else if(userId != null) {
            borrows = borrowRepository.findByUserIdContaining(pageable, userId);
        }else if(bookId != null) {
            borrows = borrowRepository.findByBookIdContaining(pageable, bookId);
        }else {
            borrows = borrowRepository.findAll(pageable);
        }
        return borrows.map(borrowMapper::toResponse);
    }

    @Override
    public BorrowResponse findById(Long id) {
        Borrow borrow = borrowRepository.findById(id)
                .orElseThrow( () -> new BorrowNotFoundException("Borrow Not Found with id: " + id) );
        return borrowMapper.toResponse(borrow);
    }

    @Override
    @Transactional
    public BorrowResponse save(CreateBorrowRequest createBorrowRequest) {
        Borrow borrow = borrowMapper.toBorrowFromCreateRequest(createBorrowRequest);
        Borrow savedBorrow = borrowRepository.save(borrow);
        return borrowMapper.toResponse(savedBorrow);
    }

    @Override
    @Transactional
    public BorrowResponse update(Long id, UpdateBorrowRequest updateBorrowRequest) {
        borrowRepository.findById(id)
                .orElseThrow( () -> new BorrowNotFoundException("Borrow Not Found with id: " + id) );
        Borrow borrow = borrowMapper.toBorrowFromUpdateRequest(updateBorrowRequest);
        borrow.setId(id);
        Borrow savedBorrow = borrowRepository.save(borrow);
        return borrowMapper.toResponse(savedBorrow);
    }

    @Override
    @Transactional
    public BorrowResponse patch(Long id, PatchBorrowRequest patchBorrowRequest) {
        Borrow borrow = borrowRepository.findById(id)
                .orElseThrow( () -> new BorrowNotFoundException("Borrow Not Found with id: " + id) );

        borrowMapper.patchBorrowFromRequest(patchBorrowRequest, borrow);

        return borrowMapper.toResponse(borrow);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        borrowRepository.findById(id)
                .orElseThrow( () -> new BorrowNotFoundException("Borrow Not Found with id: " + id) );
        borrowRepository.deleteById(id);
    }
}
