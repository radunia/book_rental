package com.example.book_rental.service;

import com.example.book_rental.persistance.Reader;
import com.example.book_rental.persistance.Rental;
import com.example.book_rental.repository.ReaderRepository;
import com.example.book_rental.repository.RentalRepository;
import com.example.book_rental.web.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Profile("reader")
public class ReaderService {

    private final ReaderRepository readerRepository;
    private final RentalRepository rentalRepository;


    public Optional<Reader> findById(Long id){
        return readerRepository.findById(id);
    }

    public List<Reader> findAll(){
        return readerRepository.findAll();
    }
    public Reader save(Reader reader){
        return readerRepository.save(reader);
    }


    public void deleteById(Long id){
        readerRepository.deleteById(id);
    }

    public List<Rental> findUserBooksById(Long id){
        Reader reader = readerRepository.findById(id).orElseThrow(UserNotFoundException::new);
        List<Rental> rentalList = reader.getRentalList();
        return rentalList;
    }

    public void rentalFee(Long id){
        Reader reader = readerRepository.findById(id).orElseThrow(UserNotFoundException::new);
//        Optional<Long> startDay = reader.getRentalList().stream().map(r -> r.getStartDay().getTime()).findFirst();
//        Optional<Long> endDay = reader.getRentalList().stream().map(r -> r.getEndDay().getTime()).findFirst();
//        List<Integer> startDay = reader.getRentalList().stream().map(r -> r.getStartDay().getDayOfYear()).toList();
//        List<Integer> endDay = reader.getRentalList().stream().map(r -> r.getEndDay().getDayOfYear()).toList();
//
//        Integer startRent = startDay.get(0);
//        Integer endRent = endDay.get(0);
//
//        int durationRent = endRent - startRent;
//
//        if(durationRent <= 7){
//            reader.getPenaltyFee().add(new BigDecimal(2));
//        }
//        for (int i = 7; i<= durationRent; i++)

//        Optional<LocalDate> startDay = reader.getRentalList().stream().map(r -> r.getStartDay()).findAny();
//        Optional<LocalDate> endDay = reader.getRentalList().stream().map(r -> r.getEndDay()).findAny();
//
//        Period.between(LocalDate.of() endDay);




//        Optional<LocalDateTime> any = reader.getRentalList().stream().map(r -> r.getStartingBooking()).findAny();
//        any.get().

    }
        

}
