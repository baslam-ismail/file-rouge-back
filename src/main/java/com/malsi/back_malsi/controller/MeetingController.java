package com.malsi.back_malsi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.malsi.back_malsi.dto.ClientDto;
import com.malsi.back_malsi.dto.MeetingDto;
import com.malsi.back_malsi.model.Meeting;
import com.malsi.back_malsi.service.MeetingService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/meetings")
public class MeetingController {
    @Autowired
    private MeetingService meetingService;

    @PostMapping("/create")
    public ResponseEntity<MeetingDto> createMeeting(@RequestBody Meeting meeting) {
        if (meeting.getDate() == null || meeting.getClient() == null || meeting.getClient().getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        MeetingDto createdMeeting = meetingService.createMeeting(meeting);
        
        return new ResponseEntity<MeetingDto>(createdMeeting, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<MeetingDto>> getMeetings() {
        List<MeetingDto> meetings = meetingService.getMeetings();

        return new ResponseEntity<List<MeetingDto>>(meetings, HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<MeetingDto> getMeetingById(@PathVariable Integer id) {
        MeetingDto meeting = meetingService.getMeetingById(id);
        if (meeting == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return new ResponseEntity<MeetingDto>(meeting, HttpStatus.OK);
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteMeeting(@PathVariable Integer id) {
        meetingService.deleteMeeting(id);

        return new ResponseEntity<String>("Meeting deleted", HttpStatus.OK);
    }

    @PutMapping("update-status/{id}")
    public ResponseEntity<MeetingDto> updateMeetingStatus(@PathVariable Integer id, @RequestParam String status) {
        MeetingDto meeting = meetingService.updateMeetingStatus(id, status);
        
        return new ResponseEntity<MeetingDto>(meeting, HttpStatus.OK);
    }

    @PutMapping("update-motif/{id}")
    public ResponseEntity<MeetingDto> updateMotif(@PathVariable Integer id, @RequestParam String motif) {
        MeetingDto meeting = meetingService.updateMeetingMotif(id, motif);
        
        return new ResponseEntity<MeetingDto>(meeting, HttpStatus.OK);
    }

    @GetMapping("/client/{meetingId}")
    public ResponseEntity<ClientDto> getClient(@PathVariable Integer meetingId) {
        ClientDto client = meetingService.getMeetingClient(meetingId);

        return new ResponseEntity<ClientDto>(client, HttpStatus.OK);
    }
    

}
