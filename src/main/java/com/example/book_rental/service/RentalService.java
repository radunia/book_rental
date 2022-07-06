package com.example.book_rental.service;

import com.example.book_rental.persistance.PhysicalBook;
import com.example.book_rental.persistance.Reader;
import com.example.book_rental.persistance.Rental;
import com.example.book_rental.repository.ReaderRepository;
import com.example.book_rental.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final ReaderService readerService;
    private final PhysicalBookService physicalBookService;
    private final ReaderRepository readerRepository;


    public Rental save(Rental rental) {
        return rentalRepository.save(rental);
    }

    public Rental findById(Long id) {
        return rentalRepository.findById(id).orElseThrow();
    }

    @Transactional
    public void rentalBook(Long readerId, Long physicalBookId) {
        PhysicalBook book = physicalBookService.findById(physicalBookId);
        Reader reader = readerService.findById(readerId);
        Rental rental = new Rental();
        rental.setPhysicalBook(book);
        reader.getRentalList().add(rental);
        readerRepository.save(reader);
    }


}
