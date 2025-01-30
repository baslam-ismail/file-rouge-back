package com.malsi.back_malsi.dto;

import com.malsi.back_malsi.model.Client;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MeetingDto {
    private Integer id;
    private String motif;
    private String date;
    private String time;
    private String status;
    private ClientDto client;
}
