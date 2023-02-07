package com.example.book_rental.service;

import com.example.book_rental.persistance.PhysicalBook;
import com.example.book_rental.persistance.Reader;
import com.example.book_rental.persistance.Rental;
import com.example.book_rental.persistance.RentalStatus;
import com.example.book_rental.repository.ReaderRepository;
import com.example.book_rental.repository.RentalRepository;
import com.example.book_rental.web.PhysicalBookNotFoundException;
import com.example.book_rental.web.RentalBookListIsToLargeException;
import com.example.book_rental.web.RentalInformationDTO;
import com.example.book_rental.web.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RentalServiceTest {

    public static final List<Rental> rentalList = new ArrayList<>(Arrays.asList(new Rental(10L, LocalDateTime.now(),LocalDate.now(), LocalDate.now(), RentalStatus.RETURNED.name(), new PhysicalBook()),
            new Rental(11L, LocalDateTime.now(), LocalDate.now(), LocalDate.now(), RentalStatus.RETURNED.name(), new PhysicalBook()),
            new Rental(12L, LocalDateTime.now(), LocalDate.now(), LocalDate.now(),RentalStatus.RETURNED.name(),new PhysicalBook()),
            new Rental(13L, LocalDateTime.now(), LocalDate.now(), LocalDate.now(),RentalStatus.RETURNED.name(),new PhysicalBook()),
            new Rental(14L, LocalDateTime.now(), LocalDate.now(), LocalDate.now(),RentalStatus.RETURNED.name(),new PhysicalBook()),
            new Rental(15L, LocalDateTime.now(), LocalDate.now(), LocalDate.now(),RentalStatus.RETURNED.name(),new PhysicalBook()),
            new Rental(16L, LocalDateTime.now(), LocalDate.now(), LocalDate.now(),RentalStatus.RETURNED.name(),new PhysicalBook()),
            new Rental(17L, LocalDateTime.now(), LocalDate.now(), LocalDate.now(),RentalStatus.RETURNED.name(),new PhysicalBook()),
            new Rental(18L, LocalDateTime.now(), LocalDate.now(), LocalDate.now(),RentalStatus.RETURNED.name(),new PhysicalBook()),
            new Rental(19L, LocalDateTime.now(), LocalDate.now(), LocalDate.now(),RentalStatus.RETURNED.name(),new PhysicalBook()),
            new Rental(20L, LocalDateTime.now(), LocalDate.now(), LocalDate.now(),RentalStatus.RETURNED.name(),new PhysicalBook())));
    private static final Reader READER = new Reader(1L, "Jan", "Kowalski", 555666777, 111, new BigDecimal(0), new ArrayList<>());
    private static final Reader READER_SECOND = new Reader(1L, "Jan", "Kowalski", 555666777, 111, new BigDecimal(0), rentalList);
    private static final PhysicalBook PHYSICAL_BOOK = new PhysicalBook(1L, "Potop", new Date(System.currentTimeMillis()), null);
    private static final Rental RENTAL = new Rental(1L, LocalDateTime.now(),LocalDate.of(2022,7,1), LocalDate.of(2022,7,7), RentalStatus.RETURNED.name(), new PhysicalBook());
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
    void shouldReturnRentalById() {
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
    void shouldRentalBook() {
        //given
        Mockito.when(readerService.findById(any())).thenReturn(Optional.of(READER));
        Mockito.when(physicalBookService.findById(any())).thenReturn(Optional.of(PHYSICAL_BOOK));
        //when
        rentalService.rentalBook(1L, 1L);
        //then
        verify(readerRepository).save(READER);

    }

    @Test
    void shouldReturnExceptionWhenListIsToLarge() {
        //given
        Mockito.when(readerService.findById(any())).thenReturn(Optional.of(READER_SECOND));
        //when & then
        assertThrows(RentalBookListIsToLargeException.class,
                () -> rentalService.rentalBook(1L,1L));

    }

    @Test
    void shouldReturnCardNumberOfTheReaderWhoReturnBook(){
        //given
        Mockito.when(readerService.findById(any())).thenReturn(Optional.of(READER));
        Mockito.when(physicalBookService.findById(any())).thenReturn(Optional.of(PHYSICAL_BOOK));
        //when
        RentalInformationDTO rentalInformationDTO = rentalService.returnBook(1L, 1L);
        //then
        assertThat(rentalInformationDTO.getCardNumber()).isEqualTo(111);
    }

    @Test
    void shouldReturnPenaltyFeeOfTheReaderWhoReturnBook(){
        //given
        Mockito.when(readerService.findById(any())).thenReturn(Optional.of(READER));
        Mockito.when(physicalBookService.findById(any())).thenReturn(Optional.of(PHYSICAL_BOOK));
        //when
        RentalInformationDTO rentalInformationDTO = rentalService.returnBook(1L, 1L);
        //then
        assertThat(rentalInformationDTO.getPenaltyFee()).isEqualTo(new BigDecimal(0));
    }
//do zmiany
    @Test
    void shouldThrownExceptionWhenUserReturnBookNotExist(){
        //given
        Mockito.when(readerService.findById(any())).thenReturn(Optional.of(READER));
//        Mockito.when(physicalBookService.findById(any())).thenReturn(Optional.of(PHYSICAL_BOOK));
        //when&then
        assertThrows(PhysicalBookNotFoundException.class, () -> rentalService.returnBook(1L, 1L));
    }


}