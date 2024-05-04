package com.example.data.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class UserShowDTO {
    private Long id;
    private String tenNguoiDung;
    private String diaChi;
    private String dienThoai;

    private String email;
    private String tenDangNhap;
}
