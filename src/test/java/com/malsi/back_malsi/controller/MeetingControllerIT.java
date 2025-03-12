package com.malsi.back_malsi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.malsi.back_malsi.model.Client;
import com.malsi.back_malsi.model.Meeting;
import org.junit.jupiter.api.Test;
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
    private ObjectMapper objectMapper;

    @Test
    public void testCreateMeetingWithValidData() throws Exception {
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
                        .content(objectMapper.writeValueAsString(meeting)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testCreateMeetingWithInvalidData() throws Exception {
        Meeting meeting = new Meeting();
        meeting.setDate(null);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/meetings/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(meeting)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testGetMeetings() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/meetings/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    public void testDeleteMeeting() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/meetings/delete/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}