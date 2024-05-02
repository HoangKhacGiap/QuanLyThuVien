package com.example.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ChiTietPhieuMuon")
public class chiTietPhieuMuon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long chitietPhieuNhap_id;

    @Column(name = "soLuongNhap", columnDefinition = "INT", nullable = false)
    private int soLuongNhap;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taiLieu_id", nullable = false)
    private taiLieu taiLieu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "phieuMuon_id", nullable = false)
    private phieuMuon phieuMuon;
}
