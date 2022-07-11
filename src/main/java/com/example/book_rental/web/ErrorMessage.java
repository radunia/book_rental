package com.example.book_rental.web;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class ErrorMessage {
    private final String message;
}
