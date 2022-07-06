package com.example.book_rental.repository;

import com.example.book_rental.persistance.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
