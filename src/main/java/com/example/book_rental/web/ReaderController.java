package com.example.book_rental.web;

import com.example.book_rental.persistance.Reader;
import com.example.book_rental.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/readers")
public class ReaderController {

    private final ReaderService readerService;

    @GetMapping("/{id}")
    ResponseEntity<Reader> getId(@PathVariable Long id) {
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

}
