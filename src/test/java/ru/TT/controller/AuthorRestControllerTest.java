package ru.TT.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser
    void getAuthorBySurnameV2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v2/authors")
                        .param("surname", "Gogol"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="admin@gmail.co", roles="ADMIN")
    void addNewAuthor() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/authors")
                .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Olga\", \"surname\": \"Primachenko\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath ("$.name").value("Olga"))
                .andExpect(jsonPath("$.surname").value("Primachenko"));
    }
    }
