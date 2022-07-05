package com.example.book_rental.service;

import com.example.book_rental.persistance.Book;
import com.example.book_rental.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;


//    public List<Book> findAll(){
//        return bookRepository.findAll();
//    }
    public Page<Book> findAll (Pageable pageable){
        return bookRepository.findAll(pageable);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }
    public void deleteById (Long id){
        bookRepository.deleteById(id);
    }
}
