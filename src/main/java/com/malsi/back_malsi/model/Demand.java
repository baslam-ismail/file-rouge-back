package com.malsi.back_malsi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "demands")
public class Demand extends BaseModel{
    @Column(name = "label", nullable = false)
    private String label;
    @Column(name = "description")
    private String description;
    @Column(name = "status", nullable = false)
    private String status;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
