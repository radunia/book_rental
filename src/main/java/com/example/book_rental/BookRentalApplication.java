package com.example.book_rental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = { SecurityAutoConfiguration.class })
public class BookRentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookRentalApplication.class, args);
	}

}
