package com.example.book_rental.service;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Data
@Configuration
@ConfigurationProperties("bookrental")
public class BookRentalConfiguration {
    private int period;
    private int penalty;
}
