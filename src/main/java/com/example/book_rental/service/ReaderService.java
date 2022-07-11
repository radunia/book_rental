package com.example.book_rental.service;

import com.example.book_rental.persistance.PhysicalBook;
import com.example.book_rental.persistance.Reader;
import com.example.book_rental.persistance.Rental;
import com.example.book_rental.repository.ReaderRepository;
import com.example.book_rental.repository.RentalRepository;
import com.example.book_rental.web.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;

    public Optional<Reader> findById(Long id){
        return readerRepository.findById(id);
    }

    public List<Reader> findAll(){
        return readerRepository.findAll();
    }
    public Reader save(Reader reader){
        return readerRepository.save(reader);
    }


    public void deleteById(Long id){
        readerRepository.deleteById(id);
    }

    public List<Rental> findUserBooksById(Long id){
        Reader reader = readerRepository.findById(id).orElseThrow(UserNotFoundException::new);
        List<Rental> rentalList = reader.getRentalList();
        return rentalList;
    }
        

}
