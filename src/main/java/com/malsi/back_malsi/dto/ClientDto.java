package com.malsi.back_malsi.dto;

import com.malsi.back_malsi.model.Demand;
import com.malsi.back_malsi.model.Meeting;
import com.malsi.back_malsi.model.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ClientDto extends PersonDto {
    private Integer id;
    private UserDto user;
    private List<DemandDto> demands;
    private List<MeetingDto> meetings;
}
