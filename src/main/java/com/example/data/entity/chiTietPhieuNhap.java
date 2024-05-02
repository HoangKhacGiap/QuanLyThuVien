package com.example.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ChiTietPhieuNhap")
public class chiTietPhieuNhap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long chitietPhieuNhap_id;

    @Column(name = "soLuongNhap", columnDefinition = "INT", nullable = false)
    private int soLuongNhap;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taiLieu_id", nullable = false)
    private taiLieu taiLieu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "phieuNhap_id", nullable = false)
    private phieuNhap phieuNhap;

//    private boolean xacNhanXoa = false;
}
