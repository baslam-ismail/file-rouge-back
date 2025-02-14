package com.malsi.back_malsi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class MeetingDto {
    private Integer id;
    private String motif;
    private String date;
    private String time;
    private String status;
    @JsonIgnore
    private ClientDto client;
}
