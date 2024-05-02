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
@Table(name = "PhieuTraSach")
public class phieuTraSach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long phieuTraSach_id;

    @Column(name = "ngayTra", columnDefinition = "NVARCHAR(250)", nullable = false, unique = true)
    private Date ngayTra;

    @Column(name = "tienPhat", columnDefinition = "LONG", nullable = false, unique = true)
    private long tienPhat = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "phieuMuon_id", nullable = false)
    private phieuMuon phieuMuon;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nguoiDung_id", nullable = false)
    private nguoiDung nguoiMuonSach;

//    private boolean xacNhanXoa = false;
}
