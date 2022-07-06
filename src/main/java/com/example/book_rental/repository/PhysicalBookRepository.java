package com.example.book_rental.repository;

import com.example.book_rental.persistance.PhysicalBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhysicalBookRepository extends JpaRepository<PhysicalBook, Long> {
}
