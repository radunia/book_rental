package com.example.book_rental.web;

import com.example.book_rental.persistance.Reader;
import com.example.book_rental.persistance.Rental;
import com.example.book_rental.repository.ReaderRepository;
import com.example.book_rental.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/readers")
public class ReaderController {

    private final ReaderService readerService;

    @GetMapping("/{id}")
    ResponseEntity<Optional<Reader>> getId(@PathVariable Long id) {
        return ResponseEntity.ok(readerService.findById(id));
    }

    @GetMapping
    ResponseEntity<List<Reader>> getAll() {
        return ResponseEntity.ok(readerService.findAll());
    }

    @PostMapping
    public ResponseEntity<Reader> save(@RequestBody Reader reader) {
        return ResponseEntity.ok(readerService.save(reader));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        readerService.deleteById(id);
    }

//    @GetMapping("/{id}/books")
//    public List<Rental> getUserBookById(@PathVariable Long id){
//        return readerService.findUserBooksById(id);
//    }


    @GetMapping("/{id}/books")
    public List<Rental> getUserBookById(@PathVariable ("id") long id){
        return readerService.findUserBooksById(id);
    }

}
