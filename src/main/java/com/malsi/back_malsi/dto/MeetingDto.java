package com.malsi.back_malsi.dto;

import lombok.Data;

@Data
public class MeetingDto {
    private Integer id;
    private String motif;
    private String date;
    private String time;
    private String status;
    private ClientDto client;
}
