package com.malsi.back_malsi.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.malsi.back_malsi.config.JwtUtils;
import com.malsi.back_malsi.model.User;
import com.malsi.back_malsi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    private JwtUtils jwtUtils;

    private String jwtToken;


    @BeforeEach
    void setUp() {
        User testUser = new User();
        testUser.setName("Test User");
        testUser.setPhone("0612345678");
        testUser.setEmail("test@example.com");
        testUser.setPassword("password123");
        testUser.setRole("client");
        userRepository.save(testUser);
        jwtToken = "Bearer " + jwtUtils.generateToken("test@example.com");
    }

    @Test
    void testCreateUser() throws Exception {
        User user = new User();
        user.setName("Test");
        user.setPhone("0612345678");
        user.setEmail("newuser@example.com");
        user.setPassword("password123");
        user.setRole("client");

        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/api/users/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson)
                        .header("Authorization", jwtToken))
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

        mockMvc.perform(get("/api/users/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", jwtToken))
                .andExpect(status().isOk())
                .andReturn();
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

        mockMvc.perform(get("/api/users/" + savedUser.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", jwtToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(savedUser.getId()))
                .andExpect(jsonPath("$.name").value("User3"));
    }
}

