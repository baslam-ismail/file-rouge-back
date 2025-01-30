package com.malsi.back_malsi.dto;

import com.malsi.back_malsi.model.Client;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserDto extends PersonDto {
    private String password;
    private String role;
    private boolean is_active;
    private boolean is_deleted;
    private List<ClientDto> clients;
}
