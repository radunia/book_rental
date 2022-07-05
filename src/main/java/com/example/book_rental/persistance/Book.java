package com.example.book_rental.persistance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name="id")
    private Long id;
//    @NotNull(message="is required")
//    @Size(min=1, message="is required")
    private String name;
    @JsonProperty("ISBN")
    private String ISBN;


}
