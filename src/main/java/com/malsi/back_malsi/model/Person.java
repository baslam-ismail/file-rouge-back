package com.malsi.back_malsi.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
@MappedSuperclass
public class Person extends BaseModel{
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "phone", nullable = false, unique = true)
    private String phone;
}
