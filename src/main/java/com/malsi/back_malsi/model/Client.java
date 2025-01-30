package com.malsi.back_malsi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "clients")
public class Client extends Person{
    @ManyToOne
    @JoinColumn(name = "commercial_id", referencedColumnName = "id")
    private User user;
    @OneToMany(mappedBy = "client")
    private List<Demand> demands;
    @OneToMany(mappedBy = "client")
    private List<Meeting> meetings;

}
