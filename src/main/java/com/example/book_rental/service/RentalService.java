package com.example.book_rental.service;

import com.example.book_rental.persistance.PhysicalBook;
import com.example.book_rental.persistance.Reader;
import com.example.book_rental.persistance.Rental;
import com.example.book_rental.repository.ReaderRepository;
import com.example.book_rental.repository.RentalRepository;
import com.example.book_rental.web.PhysicalBookNotFoundException;
import com.example.book_rental.web.UserNotFoundException;
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
        Reader reader = readerService.findById(readerId).orElseThrow(UserNotFoundException::new);
        PhysicalBook book = physicalBookService.findById(physicalBookId).orElseThrow(PhysicalBookNotFoundException::new);
        Rental rental = new Rental();
        rental.setPhysicalBook(book);
        reader.getRentalList().add(rental);
        readerRepository.save(reader);






//        Optional<Rental> result = Optional.empty();
//        Optional<Reader> reader = readerService.findById(readerId);
//        reader.ifPresent(t -> {
//            Optional<PhysicalBook> book = physicalBookService.findById(physicalBookId);
//            book.ifPresent(b -> {
//                Rental rental = new Rental();
//                rental.setPhysicalBook(b);
//                t.getRentalList().add(rental);
//                readerRepository.save(t);
//
//            });
//        });




    }


}
