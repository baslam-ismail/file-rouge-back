package com.malsi.back_malsi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class DemandDto {
    private Integer id;
    private String label;
    private String description;
    private String status;
    private String type;
    private String date;
    @JsonIgnore
    private ClientDto client;
    @JsonIgnore
    private CategoryDto category;
}
