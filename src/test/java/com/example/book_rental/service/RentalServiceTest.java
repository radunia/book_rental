package com.example.book_rental.service;

import com.example.book_rental.persistance.PhysicalBook;
import com.example.book_rental.persistance.Reader;
import com.example.book_rental.persistance.Rental;
import com.example.book_rental.repository.ReaderRepository;
import com.example.book_rental.repository.RentalRepository;
import com.example.book_rental.web.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RentalServiceTest {
    private static final Reader READER = new Reader(1L, "Jan", "Kowalski", 555666777, 111, new BigDecimal(0), new ArrayList<>());
    private static final PhysicalBook PHYSICAL_BOOK = new PhysicalBook(1L, "Potop", new Date(System.currentTimeMillis()), null);
    private static final Rental RENTAL = new Rental(1L, null, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()),null);
    @Mock
    RentalRepository rentalRepository;
    @Mock
    PhysicalBookService physicalBookService;
    @Mock
    ReaderService readerService;
    @Mock
    ReaderRepository readerRepository;

    @InjectMocks
    RentalService rentalService;

    @Test
    void shouldReturnRentalById(){
        //given
        Mockito.when(rentalRepository.findById(any())).thenReturn(Optional.of(RENTAL));
        //when
        Rental result = rentalService.findById(1L);
        //then
        assertEquals(RENTAL, result);
        assertThat(result).isEqualTo(RENTAL);
    }

    @Test
    void shouldThrownExceptionWhenUserNotExist() {
        //given
        Mockito.when(readerService.findById(any())).thenReturn(Optional.empty());
        //when&then
        assertThrows(UserNotFoundException.class, () -> rentalService.rentalBook(1L, 1L));
    }

    @Test
    void shouldRentalBook(){
        //given
        Mockito.when(readerService.findById(any())).thenReturn(Optional.of(READER));
        Mockito.when(physicalBookService.findById(any())).thenReturn(Optional.of(PHYSICAL_BOOK));
        //when
        rentalService.rentalBook(1L, 1L);
        //then
        verify(readerRepository).save(READER);

    }


}