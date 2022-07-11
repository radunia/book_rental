package com.example.book_rental.web;

import com.example.book_rental.persistance.PhysicalBook;
import com.example.book_rental.persistance.Reader;
import com.example.book_rental.persistance.Rental;
import com.example.book_rental.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;

    @GetMapping("/{id}")
    public ResponseEntity<Rental> getId(@PathVariable Long id) {
        return ResponseEntity.ok(rentalService.findById(id));
    }


    @PostMapping("/rent")
    public void rentalBook(@RequestBody RentDTO rentDTO) {
        rentalService.rentalBook(rentDTO.getReaderId(), rentDTO.getPhysicalBookId());
    }

}
