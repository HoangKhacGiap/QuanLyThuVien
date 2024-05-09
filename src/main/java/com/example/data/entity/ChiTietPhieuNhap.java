package com.example.data.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ChiTietPhieuNhap")
public class ChiTietPhieuNhap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "soLuongNhap", nullable = false)
    private int soLuongNhap;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taiLieu_id", nullable = false)
    private TaiLieu taiLieu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "phieuNhap_id", nullable = false)
    private PhieuNhap phieuNhap;

//    private boolean xacNhanXoa = false;
}
