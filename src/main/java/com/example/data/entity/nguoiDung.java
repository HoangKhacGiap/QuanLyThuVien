package com.example.data.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "NguoiDung")
public class nguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nguoiDung_id;

    @Column(name = "tenNguoiDung", columnDefinition = "NVARCHAR(250)", nullable = false)
    private String tenNguoiDung;

    @Column(name = "diaChi", columnDefinition = "NVARCHAR(250)", nullable = false)
    private String diaChi;

    @Column(name = "dienThoai", columnDefinition = "NVARCHAR(250)", nullable = false)
    private String dienThoai;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "class_id")
//    private StudentClass studentClass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taiKhoan_id", nullable = false)
    private taiKhoan taiKhoan;
}
