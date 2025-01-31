package com.malsi.back_malsi.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {
    private Integer id;
    private String label;
    private String priority;
    private List<DemandDto> demands;
}
