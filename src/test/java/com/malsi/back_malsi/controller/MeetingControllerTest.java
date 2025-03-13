package com.malsi.back_malsi.controller;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import com.malsi.back_malsi.model.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.malsi.back_malsi.dto.ClientDto;
import com.malsi.back_malsi.dto.MeetingDto;
import com.malsi.back_malsi.model.Meeting;
import com.malsi.back_malsi.service.MeetingService;

@ExtendWith(MockitoExtension.class)
public class MeetingControllerTest {

    @Mock
    private MeetingService meetingService;

    @InjectMocks
    private MeetingController meetingController;

    @Test
    public void testCreateMeeting() {
        Meeting meeting = new Meeting();
        meeting.setMotif("Test Motif");
        meeting.setDate("30-04-2025");
        meeting.setTime("10:00");
        meeting.setStatus("En attente");
        Client client = new Client();
        client.setId(1);
        meeting.setClient(client);

        MeetingDto meetingDto = new MeetingDto();
        when(meetingService.createMeeting(any(Meeting.class))).thenReturn(meetingDto);

        ResponseEntity<MeetingDto> response = meetingController.createMeeting(meeting);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(meetingDto, response.getBody());
    }

    @Test
    public void testGetMeetings() {
        List<MeetingDto> meetingList = Arrays.asList(new MeetingDto(), new MeetingDto());
        when(meetingService.getMeetings()).thenReturn(meetingList);

        ResponseEntity<List<MeetingDto>> response = meetingController.getMeetings();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(meetingList, response.getBody());
    }

    @Test
    public void testGetMeetingById() {
        MeetingDto meetingDto = new MeetingDto();
        when(meetingService.getMeetingById(eq(1))).thenReturn(meetingDto);

        ResponseEntity<MeetingDto> response = meetingController.getMeetingById(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(meetingDto, response.getBody());
    }

    @Test
    public void testDeleteMeeting() {
        ResponseEntity<String> response = meetingController.deleteMeeting(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Meeting deleted", response.getBody());
        verify(meetingService).deleteMeeting(eq(1));
    }

    @Test
    public void testUpdateMeetingStatus() {
        MeetingDto meetingDto = new MeetingDto();
        when(meetingService.updateMeetingStatus(eq(1), eq("Completed"))).thenReturn(meetingDto);

        ResponseEntity<MeetingDto> response = meetingController.updateMeetingStatus(1, "Completed");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(meetingDto, response.getBody());
    }

    @Test
    public void testUpdateMeetingMotif() {
        MeetingDto meetingDto = new MeetingDto();
        when(meetingService.updateMeetingMotif(eq(1), eq("New Motif"))).thenReturn(meetingDto);

        ResponseEntity<MeetingDto> response = meetingController.updateMotif(1, "New Motif");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(meetingDto, response.getBody());
    }

    @Test
    public void testGetClient() {
        ClientDto clientDto = new ClientDto();
        when(meetingService.getMeetingClient(eq(1))).thenReturn(clientDto);

        ResponseEntity<ClientDto> response = meetingController.getClient(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientDto, response.getBody());
    }
}