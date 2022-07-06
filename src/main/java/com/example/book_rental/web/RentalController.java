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

//    @PostMapping("/reader/{readerId}/physicalbook/{physicalBookId}")
//    public void rentalBook(@PathVariable Long readerId, @PathVariable Long physicalBookId) {
//        rentalService.rentalBook(readerId, physicalBookId);
//    }

    @PostMapping("/rent")
    public void rentalBook(@RequestBody RentDTO rentDTO) {
        rentalService.rentalBook(Long.valueOf(rentDTO.getReaderId()), Long.valueOf(rentDTO.getPhysicalBookId()));
    }

}