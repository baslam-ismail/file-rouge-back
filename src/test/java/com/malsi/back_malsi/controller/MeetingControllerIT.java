package com.malsi.back_malsi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malsi.back_malsi.config.JwtUtils;
import com.malsi.back_malsi.dto.UserDto;
import com.malsi.back_malsi.model.Client;
import com.malsi.back_malsi.model.Meeting;
import com.malsi.back_malsi.model.User;
import com.malsi.back_malsi.service.ClientService;
import com.malsi.back_malsi.service.UserService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class MeetingControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserService userService;

    @Autowired
    ClientService clientService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JwtUtils jwtUtils;

    private String jwtToken;
    @Test
    public void testCreateMeetingWithValidData() throws Exception {
        User user4 = new User();
        user4.setName("John Doe4");
        user4.setEmail("john.doe4@example.com");
        user4.setAddress("134 Main Street");
        user4.setRole("client");
        user4.setPhone("0682319754");
        user4.setPassword("Passerword1234");

        UserDto savedUserDto = userService.createUser(user4);
        User savedUser = this.modelMapper.map(savedUserDto, User.class);
        jwtToken = "Bearer " + jwtUtils.generateToken(savedUser);

        Meeting meeting = new Meeting();
        meeting.setDate("30-04-2025");
        meeting.setMotif("Création compte client");
        meeting.setStatus("En attente");
        meeting.setTime("10:00");
        Client client = new Client();
        client.setId(1);
        meeting.setClient(client);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/meetings/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", jwtToken)
                        .content(objectMapper.writeValueAsString(meeting)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testCreateMeetingWithInvalidData() throws Exception {
        User user5 = new User();
        user5.setName("John Doe5");
        user5.setEmail("john.doe5@example.com");
        user5.setAddress("135 Main Street");
        user5.setRole("client");
        user5.setPhone("0682319755");
        user5.setPassword("Passerword12345");

        UserDto savedUserDto = userService.createUser(user5);
        User savedUser = this.modelMapper.map(savedUserDto, User.class);
        jwtToken = "Bearer " + jwtUtils.generateToken(savedUser);

        Meeting meeting = new Meeting();
        meeting.setDate(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/meetings/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", jwtToken)
                        .content(objectMapper.writeValueAsString(meeting)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testGetMeetings() throws Exception {
        jwtToken = "Bearer " + jwtUtils.generateToken(savedUser);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/meetings/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void testDeleteMeeting() throws Exception {
        jwtToken = "Bearer " + jwtUtils.generateToken("john.doe4@example.com");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/meetings/delete/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", jwtToken))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}