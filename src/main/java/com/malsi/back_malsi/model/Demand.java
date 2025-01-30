package com.malsi.back_malsi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "demands")
public class Demand extends BaseModel{
    @Column(name = "label", nullable = false)
    private String label;
    @Column(name = "description")
    private String description;
    @Column(name = "status", nullable = false)
    private String status;
    @Column(name = "type", nullable = false)
    private String type;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
