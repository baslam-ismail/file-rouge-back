package com.malsi.back_malsi.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.malsi.back_malsi.model.User;
import com.malsi.back_malsi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class UserControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateUser() throws Exception {
        User user = new User();
        user.setName("Test");
        user.setPhone("0612345678");
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setRole("client");


        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/api/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.role").value("client"));

    }

    @Test
    void testGetAllUsers() throws Exception {
        User user1 = new User();
        user1.setName("User1");
        user1.setPhone("0612345690");
        user1.setEmail("user1@example.com");
        user1.setPassword("pass1");
        user1.setRole("client");

        User user2 = new User();
        user2.setName("User2");
        user2.setPhone("0612345689");
        user2.setEmail("user2@example.com");
        user2.setPassword("pass2");
        user2.setRole("client");

        userRepository.save(user1);
        userRepository.save(user2);

        mockMvc.perform(get("/api/users/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("User1"))
                .andExpect(jsonPath("$[1].name").value("User2"));
    }

    @Test
    void testGetUserById() throws Exception {
        User user3 = new User();
        user3.setName("User3");
        user3.setPhone("0612345628");
        user3.setEmail("user3@example.com");
        user3.setPassword("password123");
        user3.setRole("client");
        User savedUser = userRepository.save(user3);

        mockMvc.perform(get("/api/users/" + savedUser.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedUser.getId()))
                .andExpect(jsonPath("$.name").value("User3"));
    }
}

