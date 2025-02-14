package com.malsi.back_malsi.dto;

import lombok.Data;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
public class CategoryDto {
    private Integer id;
    private String label;
    private String priority;
    @JsonIgnore
    private List<DemandDto> demands;
}
