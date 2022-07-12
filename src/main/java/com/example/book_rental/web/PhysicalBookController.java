package com.example.book_rental.web;

import com.example.book_rental.persistance.PhysicalBook;
import com.example.book_rental.service.PhysicalBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/physicalbooks")
public class PhysicalBookController {

    private final PhysicalBookService physicalBookService;

    @GetMapping("/{id}")
    public ResponseEntity<PhysicalBook> getId(@PathVariable Long id){
        Optional<PhysicalBook> physicalBook = physicalBookService.findById(id);
        if(physicalBook.isEmpty()){
            return ResponseEntity.notFound().build();
        } else{
            return ResponseEntity.ok(physicalBook.get());
        }

    }

    @PostMapping
    public ResponseEntity<PhysicalBook> save(@RequestBody PhysicalBook physicalBook){
        return ResponseEntity.ok(physicalBookService.save(physicalBook));
    }

}
