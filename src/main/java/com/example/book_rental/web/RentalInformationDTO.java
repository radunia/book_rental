package com.example.book_rental.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class RentalInformationDTO {
    private Integer cardNumber;
    private BigDecimal penaltyFee;

}
