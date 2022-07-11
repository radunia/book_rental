package com.example.book_rental.service;

import com.example.book_rental.persistance.Reader;
import com.example.book_rental.persistance.Rental;
import com.example.book_rental.repository.ReaderRepository;
import com.example.book_rental.web.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class ReaderServiceTest {

    @Mock
    private ReaderRepository readerRepository;

    @InjectMocks
    private ReaderService readerService;

    private static final Reader READER = new Reader( 1L, "Jan", "Kowalski", 555666777,111, new BigDecimal(0), null);

    @Test
    void shouldReturnReaderById(){
        //given
        Mockito.when(readerRepository.findById(any())).thenReturn(Optional.of(READER));
        //when
        Reader result = readerService.findById(1L).orElseThrow();
        //then
        assertEquals(READER, result);
        assertThat(result).isEqualTo(READER);
    }

    @Test
    void shouldThrownExceptionWhenUserNotExist(){
        //given
        Mockito.when(readerRepository.findById(any())).thenReturn(Optional.empty());
        //when & then
        assertThrows(UserNotFoundException.class, () -> readerService.findUserBooksById(2L));
        assertThatThrownBy(() -> readerService.findUserBooksById(3L))
                .isExactlyInstanceOf(UserNotFoundException.class);

    }

    @Test
    void shouldReturnReaderRentalListById(){
        // given
        Mockito.when(readerRepository.findById(any())).thenReturn(Optional.of(READER));
        //when
        List<Rental> result = readerService.findUserBooksById(2L);
        //then
        assertEquals(READER.getRentalList(), result);
    }

}