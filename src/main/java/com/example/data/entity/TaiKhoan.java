package com.example.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TaiKhoan")
public class TaiKhoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tenDangNhap", columnDefinition = "NVARCHAR(250)", nullable = false, unique = true)
    private String tenDangNhap;

    @Column(name = "email", columnDefinition = "NVARCHAR(250)", nullable = false, unique = true)
    private String email;

    @JsonIgnore
    @Column(name = "matKhau", columnDefinition = "NVARCHAR(250)", nullable = false)
    private String matKhau;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "phanQuyen_id", nullable = false)
    private PhanQuyen phanQuyen;

    private boolean xacNhanXoa = false;
}
