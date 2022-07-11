package com.example.book_rental.web;

import com.example.book_rental.persistance.PhysicalBook;
import com.example.book_rental.service.PhysicalBookService;
import com.example.book_rental.service.ReaderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest( value = PhysicalBookService.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
class PhysicalBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PhysicalBookService physicalBookService;

    private static final PhysicalBook PHYSICAL_BOOK = new PhysicalBook(1L, "Potop", new Date(System.currentTimeMillis()), null);


    @Test
    void savePhysicalBook() throws Exception {
        when(physicalBookService.save(PHYSICAL_BOOK)).thenReturn(PHYSICAL_BOOK);

        this.mockMvc.perform(post("/physicalbooks")
                .content(asJsonString(new PhysicalBook(1L, "Potop", new Date(System.currentTimeMillis()), null)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk());
//                .andExpect(MockMvcResultMatchers.jsonPath("$.physicalBookId").exists())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Potop"));

    }



    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}