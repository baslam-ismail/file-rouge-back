package com.malsi.back_malsi.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClientDto extends PersonDto {
    private Integer id;
    private UserDto user;
    private List<DemandDto> demands;
    private List<MeetingDto> meetings;
}
