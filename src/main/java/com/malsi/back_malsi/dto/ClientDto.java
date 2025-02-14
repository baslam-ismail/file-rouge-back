package com.malsi.back_malsi.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@EqualsAndHashCode(callSuper = false)
public class ClientDto extends PersonDto {
    private Integer id;
    @JsonIgnore
    private UserDto user;
    @JsonIgnore
    private List<DemandDto> demands;
    @JsonIgnore
    private List<MeetingDto> meetings;
}
