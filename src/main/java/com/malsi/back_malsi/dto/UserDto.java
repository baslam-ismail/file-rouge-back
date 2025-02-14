package com.malsi.back_malsi.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDto extends PersonDto {
    private String password;
    private String role;
    private boolean is_active;
    private boolean is_deleted;
    @JsonIgnore
    private List<ClientDto> clients;
}
