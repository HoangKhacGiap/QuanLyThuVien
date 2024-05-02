package com.example.data.entity;

import jakarta.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PhanQuyen")
public class phanQuyen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenPhanQuyen", columnDefinition = "NVARCHAR(25)", nullable = false, unique = true)
    private String tenPhanQuyen;
}
