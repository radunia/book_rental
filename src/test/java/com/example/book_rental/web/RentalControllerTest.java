package com.example.book_rental.web;

import com.example.book_rental.persistance.Rental;
import com.example.book_rental.persistance.RentalStatus;
import com.example.book_rental.service.RentalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
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

    private static final Rental RENTAL = new Rental(1L, null, LocalDate.now(), LocalDate.now(), RentalStatus.RETURNED.name(), null);
    private static final RentalInformationDTO RENTAL_INFORMATION_DTO = new RentalInformationDTO(111, new BigDecimal(4));

    @Test
    public void shouldNotSaveRental() throws Exception{
        when(rentalService.save(RENTAL)).thenReturn(RENTAL);

        this.mockMvc.perform(post("/rentals")
                .content(asJsonString(new Rental(1L, null, LocalDate.of(2022,7,2), LocalDate.of(2022,7,11), RentalStatus.RETURNED.name(), null)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnAmountOfPenaltyForTooLongRentalBook(){
        when(rentalService.returnBook(1L, 1L)).thenReturn(RENTAL_INFORMATION_DTO);

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}