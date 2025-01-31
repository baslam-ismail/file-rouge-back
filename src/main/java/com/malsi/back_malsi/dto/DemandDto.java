package com.malsi.back_malsi.dto;

import lombok.Data;

@Data
public class DemandDto {
    private Integer id;
    private String label;
    private String description;
    private String status;
    private String type;
    private String date;
    private ClientDto client;
    private CategoryDto category;
}
