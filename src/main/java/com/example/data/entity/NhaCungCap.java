package com.example.data.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "NhaCungCap")
public class NhaCungCap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tenNhaCungCap", columnDefinition = "NVARCHAR(250)", nullable = false)
    private String tenNhaCungCap;

    @Column(name = "diaChi", columnDefinition = "NVARCHAR(250)", nullable = false)
    private String diaChi;

    @Column(name = "dienThoai", columnDefinition = "NVARCHAR(250)", nullable = false, unique = true)
    private String dienThoai;

    @Column(name = "email", columnDefinition = "NVARCHAR(250)", nullable = false, unique = true)
    private String email;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "phanQuyen_id", nullable = false)
//    private phanQuyen phanQuyen;
//
//    private boolean xacNhanXoa = false;
}
