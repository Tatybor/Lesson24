package ru.TT.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.security.test.context.support.WithMockUser;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAuthorBySurnameV2() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/v2/authors"))
                .with(httpBasic())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void getAuthorBySurnameV3() {
    }

    @Test
    void addNewAuthor() {
    }
}