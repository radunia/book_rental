package com.example.book_rental.service;

import com.example.book_rental.persistance.PhysicalBook;
import com.example.book_rental.persistance.Reader;
import com.example.book_rental.persistance.Rental;
import com.example.book_rental.persistance.RentalStatus;
import com.example.book_rental.repository.ReaderRepository;
import com.example.book_rental.repository.RentalRepository;
import com.example.book_rental.web.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RentalService {
    private final RentalRepository rentalRepository;
    private final ReaderService readerService;
    private final PhysicalBookService physicalBookService;
    private final ReaderRepository readerRepository;
    private final BookRentalConfiguration bookRentalConfiguration;


    public Rental save(Rental rental) {
        return rentalRepository.save(rental);
    }

    public Rental findById(Long id) {
        return rentalRepository.findById(id).orElseThrow();
    }

    public RentalInformationDTO returnBook(Long readerId, Long physicalBookId){
        Reader reader = readerService.findById(readerId).orElseThrow(UserNotFoundException::new);
        PhysicalBook book = physicalBookService.findById(physicalBookId).orElseThrow(PhysicalBookNotFoundException::new);
        Optional<Rental> rental = reader.getRentalList().stream().filter(t -> t.getPhysicalBook().equals(book))
                .findAny();

        rental.ifPresent((r) ->{
            LocalDate startDay = r.getStartDay();
            LocalDate returnDay = LocalDate.now();
            r.setStatus(RentalStatus.RETURNED.name());
            Period betweenRentDate = Period.between(startDay, returnDay);
            if (betweenRentDate.getDays()>bookRentalConfiguration.getPeriod()){
                int days = betweenRentDate.getDays() - bookRentalConfiguration.getPeriod();
                reader.getPenaltyFee().add(new BigDecimal(days*(bookRentalConfiguration.getPenalty())));

            }

        });

        RentalInformationDTO rentalInformationDTO = new RentalInformationDTO();
        rentalInformationDTO.setCardNumber(reader.getCardNumber());
        rentalInformationDTO.setPenaltyFee(reader.getPenaltyFee());
        return rentalInformationDTO;

        //return null;

//                int i = betweenRentDate.getDays();
//                while(i>14){
//                    reader.getPenaltyFee().add(new BigDecimal(1));
//                    i--;
//                }



//        String startDay = String.valueOf(reader.getRentalList().stream().map(t -> t.getStartDay().toString()).findAny());
//        Optional<String> starrrt = reader.getRentalList().stream().map(t -> t.getStartDay().toString()).findAny();
//        String returnDay = LocalDate.now().toString();
//        LocalDate zwrot = LocalDate.now();
//
//        Period.between(LocalDate.ofInstant(starrrt), zwrot);
//        Period.between(startDay, returnDay);
    }

    @Transactional
    public void rentalBook(Long readerId, Long physicalBookId) {
        Reader reader = readerService.findById(readerId).orElseThrow(UserNotFoundException::new);
        int amountRentalBooks = reader.getRentalList().size();
        if (amountRentalBooks>10) {
            throw new RentalBookListIsToLargeException();
        }
        PhysicalBook book = physicalBookService.findById(physicalBookId).orElseThrow(PhysicalBookNotFoundException::new);
        Rental rental = new Rental();
        rental.setPhysicalBook(book);
        reader.getRentalList().add(rental);
        rental.setStatus(RentalStatus.RENTED.name());
        readerRepository.save(reader);


//        try{
//            amountRentalBooks=11;
//        } catch (RentalBookListIsToLargeException ex){
//            ex.printStackTrace();
//            throw new RentalBookListIsToLargeException();
//        }

//        Optional<Rental> result = Optional.empty();
//        Optional<Reader> reader = readerService.findById(readerId);
//        reader.ifPresent(t -> {
//            Optional<PhysicalBook> book = physicalBookService.findById(physicalBookId);
//            book.ifPresent(b -> {
//                Rental rental = new Rental();
//                rental.setPhysicalBook(b);
//                t.getRentalList().add(rental);
//                readerRepository.save(t);
//
//            });
//        });
    }




}
