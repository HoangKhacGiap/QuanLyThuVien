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
@Table(name = "PhieuNhap")
public class phieuNhap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long phieuNhap_id;

    @Column(name = "ngayNhap", columnDefinition = "NVARCHAR(250)", nullable = false, unique = true)
    private Date ngayNhap;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nhaCungCap_id", nullable = false)
    private nhaCungCap nhaCungCap;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nguoiDung_id", nullable = false)
    private nguoiDung nguoiDung;

    private boolean xacNhanXoa = false;
}
