package com.malsi.back_malsi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class DemandDto {
    private Integer id;
    private String label;
    private String description;
    private String status;
    @JsonIgnore
    private ClientDto client;
    @JsonIgnore
    private CategoryDto category;
}
