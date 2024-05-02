package com.example.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TaiLieu")
public class taiLieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taiLieu_id;

    @Column(name = "tenNguoiDung", columnDefinition = "NVARCHAR(250)", nullable = false)
    private String tenNguoiDung;

    @Column(name = "tenTacGia", columnDefinition = "NVARCHAR(250)", nullable = false)
    private String tenTacGia;

    @Column(name = "ngonNgu", columnDefinition = "NVARCHAR(250)", nullable = false)
    private String ngonNgu;

    @Column(name = "theLoai", columnDefinition = "NVARCHAR(250)", nullable = false)
    private String theLoai;

    @Column(name = "nhaXuatBan", columnDefinition = "NVARCHAR(250)", nullable = false)
    private String nhaXuatBan;

    @Column(name = "noiDung", columnDefinition = "NVARCHAR(2048)", nullable = false)
    private String noiDung;

    @Column(name = "soLuongTon", columnDefinition = "INT", nullable = false)
    private int soLuongTon;

    @Column(name = "hinhAnhDaiDien", columnDefinition = "NVARCHAR(250)", nullable = false)
    private String hinhAnhDaiDien;

    private boolean xacNhanXoa = false;

}
