package com.malsi.back_malsi.dto;

import com.malsi.back_malsi.model.Demand;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryDto {
    private Integer id;
    private String label;
    private String priority;
    private List<DemandDto> demands;
}
