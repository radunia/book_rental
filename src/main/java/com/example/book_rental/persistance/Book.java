package com.example.book_rental.persistance;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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
    private String description;
    @JsonProperty("ISBN")
    private String ISBN;


}
