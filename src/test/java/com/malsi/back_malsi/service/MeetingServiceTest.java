package com.malsi.back_malsi.service;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.malsi.back_malsi.dto.ClientDto;
import com.malsi.back_malsi.dto.MeetingDto;
import com.malsi.back_malsi.model.Client;
import com.malsi.back_malsi.model.Meeting;
import com.malsi.back_malsi.repository.ClientRepository;
import com.malsi.back_malsi.repository.MeetingRepository;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class MeetingServiceTest {

    @Mock
    private MeetingRepository meetingRepository;

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private MeetingService meetingService;

    @Test
    public void testCreateMeeting() {
        Meeting meeting = new Meeting();
        Client client = new Client();
        client.setId(1);
        meeting.setClient(client);
        MeetingDto meetingDto = new MeetingDto();

        when(clientRepository.findById(eq(1))).thenReturn(Optional.of(client));
        when(meetingRepository.save(any(Meeting.class))).thenReturn(meeting);
        when(modelMapper.map(any(Meeting.class), eq(MeetingDto.class))).thenReturn(meetingDto);

        MeetingDto result = meetingService.createMeeting(meeting);

        assertEquals(meetingDto, result);
        verify(meetingRepository).save(meeting);
    }

    @Test
    public void testGetMeetingById() {
        Meeting meeting = new Meeting();
        MeetingDto meetingDto = new MeetingDto();

        when(meetingRepository.findById(any(Integer.class))).thenReturn(Optional.of(meeting));
        when(modelMapper.map(any(Meeting.class), eq(MeetingDto.class))).thenReturn(meetingDto);

        MeetingDto result = meetingService.getMeetingById(1);

        assertEquals(meetingDto, result);
    }

    @Test
    public void testDeleteMeeting() {
        meetingService.deleteMeeting(1);

        verify(meetingRepository).deleteById(eq(1));
    }

    @Test
    public void testGetMeetings() {
        List<Meeting> meetings = Arrays.asList(new Meeting(), new Meeting());
        List<MeetingDto> meetingDtos = Arrays.asList(new MeetingDto(), new MeetingDto());

        when(meetingRepository.findAll()).thenReturn(meetings);
        when(modelMapper.map(any(Meeting.class), eq(MeetingDto.class))).thenReturn(meetingDtos.get(0), meetingDtos.get(1));

        List<MeetingDto> result = meetingService.getMeetings();

        assertEquals(meetingDtos, result);
    }

    @Test
    public void testGetClientMeetings() {
        Client client = new Client();
        client.setId(1);
        List<Meeting> meetings = Arrays.asList(new Meeting(), new Meeting());
        client.setMeetings(meetings);
        ClientDto clientDto = new ClientDto();
        clientDto.setMeetings(Arrays.asList(new MeetingDto(), new MeetingDto()));

        when(clientRepository.findById(eq(1))).thenReturn(Optional.of(client));
        when(modelMapper.map(client, ClientDto.class)).thenReturn(clientDto);

        List<MeetingDto> result = meetingService.getClientMeetings(1);

        assertEquals(clientDto.getMeetings(), result);
    }

    @Test
    public void testUpdateMeetingStatus() {
        Meeting meeting = new Meeting();
        meeting.setStatus("Scheduled");
        MeetingDto meetingDto = new MeetingDto();

        when(meetingRepository.findById(any(Integer.class))).thenReturn(Optional.of(meeting));
        when(meetingRepository.save(any(Meeting.class))).thenReturn(meeting);
        when(modelMapper.map(any(Meeting.class), eq(MeetingDto.class))).thenReturn(meetingDto);

        MeetingDto result = meetingService.updateMeetingStatus(1, "Scheduled");

        assertEquals(meetingDto, result);
    }

    @Test
    public void testUpdateMeetingMotif() {
        Meeting meeting = new Meeting();
        meeting.setMotif("Initial Meeting");
        MeetingDto meetingDto = new MeetingDto();

        when(meetingRepository.findById(any(Integer.class))).thenReturn(Optional.of(meeting));
        when(meetingRepository.save(any(Meeting.class))).thenReturn(meeting);
        when(modelMapper.map(any(Meeting.class), eq(MeetingDto.class))).thenReturn(meetingDto);

        MeetingDto result = meetingService.updateMeetingMotif(1, "Initial Meeting");

        assertEquals(meetingDto, result);
    }

    @Test
    public void testGetMeetingClient() {
        Meeting meeting = new Meeting();
        Client client = new Client();
        meeting.setClient(client);
        ClientDto clientDto = new ClientDto();

        when(meetingRepository.findById(any(Integer.class))).thenReturn(Optional.of(meeting));
        when(modelMapper.map(any(Client.class), eq(ClientDto.class))).thenReturn(clientDto);

        ClientDto result = meetingService.getMeetingClient(1);

        assertEquals(clientDto, result);
    }
}