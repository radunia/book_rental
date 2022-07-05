package com.example.book_rental.web;

import com.example.book_rental.persistance.Book;
import com.example.book_rental.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

//    @GetMapping
//    public ResponseEntity<List<Book>> getAll(){
//        return ResponseEntity.ok(bookService.findAll());
//    }

    @GetMapping
    public ResponseEntity<Page<Book>> getAll(@RequestParam int size, @RequestParam int page) {
        if (size == 0) {
            size = 25;
        }
        if (page == 0) {
            page = 1;
        }
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(bookService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody Book book){
        return ResponseEntity.ok(bookService.save(book));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        bookService.deleteById(id);
    }

}
