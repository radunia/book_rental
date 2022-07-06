package com.example.book_rental.web;

import com.example.book_rental.persistance.PhysicalBook;
import com.example.book_rental.service.PhysicalBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/physicalbooks")
public class PhysicalBookController {

    private final PhysicalBookService physicalBookService;

    @GetMapping("/{id}")
    public ResponseEntity<PhysicalBook> getId(@PathVariable Long id){
        return ResponseEntity.ok(physicalBookService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PhysicalBook> save(@RequestBody PhysicalBook physicalBook){
        return ResponseEntity.ok(physicalBookService.save(physicalBook));
    }

}
