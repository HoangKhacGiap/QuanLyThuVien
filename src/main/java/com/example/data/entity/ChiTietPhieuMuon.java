package com.example.data.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ChiTietPhieuMuon")
public class ChiTietPhieuMuon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "taiLieu_id", nullable = false)
    private TaiLieu taiLieu;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "phieuMuon_id", nullable = false)
    private PhieuMuon phieuMuon;
}
