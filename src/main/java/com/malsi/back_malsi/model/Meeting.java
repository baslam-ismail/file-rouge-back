package com.malsi.back_malsi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "meetings")
public class Meeting extends BaseModel{
    @Column(name = "motif", nullable = false)
    private String motif;
    @Column(name = "date", nullable = false)
    private String date;
    @Column(name = "time", nullable = false)
    private String time;
    @Column(name = "status", nullable = false)
    private String status;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
