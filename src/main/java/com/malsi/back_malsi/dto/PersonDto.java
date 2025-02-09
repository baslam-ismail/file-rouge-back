package com.malsi.back_malsi.dto;

import java.time.Instant;

import lombok.Data;

@Data
public class PersonDto {
    private Integer id;
    private String name;
    private String email;
    private String address;
    private String phone;
    private Instant created_at;
    private Instant updated_at;

}
