package com.united.demo.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.united.demo.domain.Author;
import com.united.demo.domain.dto.AuthorDto;
import com.united.demo.services.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@ExtendWith(SpringExtension.class) // probati bez ovoga
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class AuthorControllerIntegrationTests {

    private AuthorService authorService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @Autowired
    public AuthorControllerIntegrationTests(AuthorService authorService, MockMvc mockMvc, ObjectMapper objectMapper) {
        this.authorService = authorService;
        this.mockMvc = mockMvc;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatGetAuthorsReturn200OKEmptyResult() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/authors")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("[]"));

    }

    @Test
    public void testThatGetAuthorsReturnArrayOfAuthors() throws Exception {
        Author a1 = Author.builder().email("a1@email.com").firstName("a1").lastName("a1").build();
        Author a2 = Author.builder().email("a2@email.com").firstName("a2").lastName("a2").build();

        authorService.save(a1);
        authorService.save(a2);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/authors")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetAuthorByIdReturnStatus200OK() throws Exception {
        Author a1 = Author.builder().email("a1@email.com").firstName("a1").lastName("a1").build();
        authorService.save(a1);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/authors/" + a1.getId())
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(a1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(a1.getEmail()));
    }

    @Test
    public void testGetAuthorReturns404NotFound() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/authors/222")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void testCreateAuthorReturns201Created() throws Exception {
        AuthorDto a1 = AuthorDto.builder().email("a1@email.com").firstName("a1").lastName("a1").build();
        String a1Json = objectMapper.writeValueAsString(a1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON).content(a1Json)
                ).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNumber());
    }

    @Test
    public void testCreateThatAlreadyExists() throws Exception {
        Author a1 = Author.builder().email("a1@email.com").firstName("a1").lastName("a1").build();
        authorService.save(a1);
        String a1Json = objectMapper.writeValueAsString(a1);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/authors")
                        .contentType(MediaType.APPLICATION_JSON).content(a1Json))
                .andExpect(MockMvcResultMatchers.status().isConflict());
    }

}
