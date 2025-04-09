package com.malsi.back_malsi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malsi.back_malsi.config.JwtUtils;
import com.malsi.back_malsi.dto.UserDto;
import com.malsi.back_malsi.model.Client;
import com.malsi.back_malsi.model.User;
import com.malsi.back_malsi.service.ClientService;
import com.malsi.back_malsi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserService userService;

    @Autowired
    ClientService clientService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private JwtUtils jwtUtils;

    private String jwtToken;


    @Test
    public void testCreateClientWithValidData() throws Exception {
        User user = new User();
        user.setName("John Doe");
        user.setEmail("john.doe@example.com");
        user.setAddress("123 Main Street");
        user.setRole("client");
        user.setPhone("123456789");
        user.setPassword("Passer123");

        UserDto savedUserDto = userService.createUser(user);
        User savedUser = this.modelMapper.map(savedUserDto, User.class);
        jwtToken = "Bearer " + jwtUtils.generateToken(savedUser);

        Client client = new Client();
        client.setName("John Doe Client");
        client.setEmail("john.client@example.com");
        client.setAddress("456 Client Street");
        client.setPhone("987654321");
        client.setUser(savedUser);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/clients/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", jwtToken)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated());
    }


    @Test
    public void testCreateClientWithInvalidData() throws Exception {
        User user1 = new User();
        user1.setName("John Doe1");
        user1.setEmail("john.doe1@example.com");
        user1.setAddress("13 Main Street");
        user1.setRole("client");
        user1.setPhone("012345678");
        user1.setPassword("Passerword123");

        UserDto savedUserDto = userService.createUser(user1);
        User savedUser = this.modelMapper.map(savedUserDto, User.class);
        jwtToken = "Bearer " + jwtUtils.generateToken(savedUser);

        Client client = new Client();
        client.setEmail(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/clients/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", jwtToken)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
