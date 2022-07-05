package com.example.book_rental.service;

import com.example.book_rental.persistance.Reader;
import com.example.book_rental.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {

    private final ReaderRepository readerRepository;

    public Reader findById(Long id){
        return readerRepository.findById(id).orElseThrow();
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
}
