package com.example.book_rental.web;

import com.example.book_rental.persistance.Reader;
import com.example.book_rental.service.ReaderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest( value = ReaderController.class, excludeAutoConfiguration = { SecurityAutoConfiguration.class })
class ReaderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReaderService readerService;

    private static final Reader READER = new Reader( 1L, "Jan", "Kowalski", 555666777,111, new BigDecimal(0), null);


    @Test
    public void shouldReturnReaderFromService() throws Exception {
        when(readerService.findById(1L)).thenReturn(Optional.of(READER));

        this.mockMvc.perform(get("/readers/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void shouldNotFoundReaderFromService() throws Exception {
        when(readerService.findById(5L)).thenReturn(Optional.of(READER));

        this.mockMvc.perform(get("/readers/2")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    public void saveReader() throws Exception {
        when(readerService.save(READER)).thenReturn(READER);

        this.mockMvc.perform(post("/readers")
                .content(asJsonString(new Reader(1L, "firstName4", "lastName4", 6658745, 5584, null, null)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk());

    }

    @Test
    public void deleteReader() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/readers/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.physicalBookId").doesNotExist());
    }


//    @Test
//    public void deleteReaderWhenNotExists() throws Exception{
//        mockMvc.perform(MockMvcRequestBuilders.delete("/readers/10"))
//                .andExpect(status().isInternalServerError());
//    }

    @Test
    void getAll() {
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}