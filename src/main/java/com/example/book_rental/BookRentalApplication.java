package com.example.book_rental;

import com.example.book_rental.web.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication (exclude = { SecurityAutoConfiguration.class })
public class BookRentalApplication {


	public static void main(String[] args) {
		SpringApplication.run(BookRentalApplication.class, args);
	}

}
