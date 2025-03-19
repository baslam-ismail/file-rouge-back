package com.malsi.back_malsi.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.malsi.back_malsi.dto.ClientDto;
import com.malsi.back_malsi.dto.MeetingDto;
import com.malsi.back_malsi.model.Client;
import com.malsi.back_malsi.model.Meeting;
import com.malsi.back_malsi.repository.ClientRepository;
import com.malsi.back_malsi.repository.MeetingRepository;

@Service
public class MeetingService {
    @Autowired
    private MeetingRepository meetingRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ClientRepository clientRepository;
    
    public MeetingDto createMeeting(Meeting meeting) {
        Client client = clientRepository.findById(meeting.getClient().getId()).orElse(null);
        meeting.setClient(client);

        return this.modelMapper.map(this.meetingRepository.save(meeting), MeetingDto.class);
    }

    public MeetingDto getMeetingById(Integer id) {
        Meeting meeting = this.meetingRepository.findById(id).orElse(null);
        if (meeting == null) {
            return null;
        }

        return this.modelMapper.map(meeting, MeetingDto.class);
    }

    public void deleteMeeting(Integer id) {
        this.meetingRepository.deleteById(id);
    }

    public List<MeetingDto> getMeetings() {
        List<Meeting> meetings = this.meetingRepository.findAll();
        if (meetings == null) {
            return null;
        }

        List<MeetingDto> meetingsDto = new ArrayList<>();

        for (Meeting meeting : meetings) {
            meetingsDto.add(this.modelMapper.map(meeting, MeetingDto.class));
        }

        return meetingsDto;
    }

    public List<MeetingDto> getClientMeetings(Integer clientId) {
        ClientDto client = this.modelMapper.map(this.clientRepository.findById(clientId).orElse(null), ClientDto.class);
        if (client == null) {
            return null;
        }
        List<MeetingDto> meetings = client.getMeetings();

        return meetings;
    }

    public MeetingDto updateMeetingStatus(Integer id, String status) {
        Meeting meeting = this.meetingRepository.findById(id).orElse(null);
        if (meeting == null) {
            return null;
        }
        meeting.setStatus(status);

        return this.modelMapper.map(this.meetingRepository.save(meeting), MeetingDto.class);
    }

    public MeetingDto updateMeetingMotif(Integer id, String motif) {
        Meeting meeting = this.meetingRepository.findById(id).orElse(null);
        if (meeting == null) {
            return null;
        }
        meeting.setMotif(motif);

        return this.modelMapper.map(this.meetingRepository.save(meeting), MeetingDto.class);
    }

    public ClientDto getMeetingClient(Integer id) {
        Meeting meeting = this.meetingRepository.findById(id).orElse(null);
        if (meeting == null) {
            return null;
        }
        ClientDto client = this.modelMapper.map(meeting.getClient(), ClientDto.class);

        return client;
    }

}
