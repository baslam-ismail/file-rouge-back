package com.malsi.back_malsi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends Person{
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role", nullable = false)
    private String role;
    @Column(name = "is_active", nullable = false, columnDefinition = "boolean default true")
    private boolean is_active = true;
    @Column(name = "is_deleted", nullable = false, columnDefinition = "boolean default false")
    private boolean is_deleted = false;
    @OneToMany(mappedBy = "user")
    private List<Client> clients;
}
