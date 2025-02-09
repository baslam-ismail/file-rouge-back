package com.malsi.back_malsi.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserDto extends PersonDto {
    private String password;
    private String role;
    private boolean is_active;
    private boolean is_deleted;
    private List<ClientDto> clients;
}
