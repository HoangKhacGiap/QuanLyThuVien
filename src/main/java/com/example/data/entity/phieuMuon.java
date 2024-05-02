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
@Table(name = "PhieuMuon")
public class phieuMuon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long phieuMuon_id;

    @Column(name = "ngayMuon", columnDefinition = "NVARCHAR(250)", nullable = false, unique = true)
    private Date ngayMuon;

    @Column(name = "hanMuon", columnDefinition = "NVARCHAR(250)", nullable = false, unique = true)
    private Date hanMuon;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nguoiDung_id", nullable = false)
    private nguoiDung nguoiXacNhanPhieuMuon;

    private boolean xacNhanMuon = false;

}
