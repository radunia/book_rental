package com.example.book_rental.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<ErrorMessage> userNotFound (UserNotFoundException ex){
       return ResponseEntity.badRequest().body(new ErrorMessage("reader not found"));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {PhysicalBookNotFoundException.class})
    public ResponseEntity<ErrorMessage> physicalBookNotFound(PhysicalBookNotFoundException ex){
        return ResponseEntity.badRequest().body(new ErrorMessage("book not found"));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {RentalBookListIsToLargeException.class})
    public ResponseEntity<ErrorMessage> rentalBookListIsToLarge(RentalBookListIsToLargeException ex){
        return ResponseEntity.badRequest().body(new ErrorMessage("Unfortunately you cannot borrow another book. The limit is 10."));
    }

}
