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
@Table(name = "PhieuNhap")
public class PhieuNhap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ngayNhap")
    private Date ngayNhap;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nhaCungCap_id", nullable = false)
    private NhaCungCap nhaCungCap;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nguoiDung_id", nullable = false)
    private NguoiDung nguoiDung;

    private boolean xacNhanXoa = false;
}
