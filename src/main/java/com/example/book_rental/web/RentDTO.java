package com.example.book_rental.web;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RentDTO {
    private final Long readerId;
    private final Long physicalBookId;
}
