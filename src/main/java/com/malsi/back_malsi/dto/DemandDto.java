package com.malsi.back_malsi.dto;

import com.malsi.back_malsi.model.Category;
import com.malsi.back_malsi.model.Client;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
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
