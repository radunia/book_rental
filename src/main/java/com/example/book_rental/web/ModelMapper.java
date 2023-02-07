package com.example.book_rental.web;

import com.example.book_rental.persistance.Reader;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {
    public RentalInformationDTO toDTO(Reader reader){
        RentalInformationDTO rentalInformationDTO = new RentalInformationDTO();
        rentalInformationDTO.setCardNumber(reader.getCardNumber());
        rentalInformationDTO.setPenaltyFee(reader.getPenaltyFee());
        return rentalInformationDTO;
    }
}
