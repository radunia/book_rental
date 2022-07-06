package com.example.book_rental.persistance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="id")
    private Long id;
//    @NotNull(message="is required")
//    @Size(min=1, message="is required")
    private String firstName;
//    @NotNull(message="is required")
//    @Size(min=1, message="is required")
    private String lastName;
//    @Range(min = 9, max = 9)
    private Integer phoneNumber;
    private Integer cardNumber;
    private BigDecimal penaltyFee;

    @OneToMany (cascade = {CascadeType.ALL})
    private List<Rental> rentalList;
}
