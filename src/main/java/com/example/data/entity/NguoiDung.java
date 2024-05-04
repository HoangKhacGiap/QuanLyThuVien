package com.example.data.entity;

import javax.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "NguoiDung")
public class NguoiDung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tenNguoiDung", columnDefinition = "NVARCHAR(250)")
    private String tenNguoiDung;

    @Column(name = "diaChi", columnDefinition = "NVARCHAR(250)")
    private String diaChi;

    @Column(name = "dienThoai", columnDefinition = "NVARCHAR(250)")
    private String dienThoai;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "class_id")
//    private StudentClass studentClass;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "taiKhoan_id", nullable = false)
    private TaiKhoan taiKhoan;
}
