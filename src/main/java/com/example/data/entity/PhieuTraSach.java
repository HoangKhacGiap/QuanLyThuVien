package com.example.data.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PhieuTraSach")
public class PhieuTraSach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ngayTra")
    private Date ngayTra;

    @Column(name = "tienPhat")
    private long tienPhat = 0;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "phieuMuon_id", nullable = false)
    private PhieuMuon phieuMuon;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nguoiDung_id", nullable = false)
    private NguoiDung nguoiMuonSach;

//    private boolean xacNhanXoa = false;
}
