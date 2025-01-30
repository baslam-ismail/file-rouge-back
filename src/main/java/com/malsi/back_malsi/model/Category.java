package com.malsi.back_malsi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class Category extends BaseModel{
    @Column(name = "label", nullable = false)
    private String label;
    @Column(name = "priority", nullable = false)
    private String priority;
    @OneToMany(mappedBy = "category")
    private List<Demand> demands;
}
