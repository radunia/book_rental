package com.example.book_rental.web;

import com.example.book_rental.persistance.Rental;
import com.example.book_rental.service.RentalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest( value = RentalController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
class RentalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalService rentalService;

    private static final Rental RENTAL = new Rental(1L, null, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),null);

    @Test
    public void shouldNotSaveRental() throws Exception{
        when(rentalService.save(RENTAL)).thenReturn(RENTAL);

        this.mockMvc.perform(post("/rentals")
                .content(asJsonString(new Rental(1L, null, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),null)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
    @Test
    void rentalBook() {
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}