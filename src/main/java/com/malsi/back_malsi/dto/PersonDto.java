package com.malsi.back_malsi.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonDto {
    private Integer id;
    private String name;
    private String email;
    private String address;
    private String phone;
}
