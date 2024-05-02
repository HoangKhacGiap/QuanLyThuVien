package com.example.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TaiKhoan")
public class taiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long taiKhoan_id;

    @Column(name = "tenDangNhap", columnDefinition = "NVARCHAR(250)", nullable = false, unique = true)
    private String tenDangNhap;

    @Column(name = "email", columnDefinition = "NVARCHAR(250)", nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(name = "matKhau", columnDefinition = "NVARCHAR(250)", nullable = false)
    private String matKhau;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "phanQuyen_id", nullable = false)
    private phanQuyen phanQuyen;

    private boolean xacNhanXoa = false;
}
