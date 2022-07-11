package com.example.book_rental.service;

import com.example.book_rental.persistance.Book;
import com.example.book_rental.persistance.PhysicalBook;
import com.example.book_rental.repository.PhysicalBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhysicalBookService {
    private final PhysicalBookRepository physicalBookRepository;

    public Optional<PhysicalBook> findById(Long id){
        return physicalBookRepository.findById(id);
    }

    public PhysicalBook save(PhysicalBook physicalBook){
        return physicalBookRepository.save(physicalBook);
    }
}
