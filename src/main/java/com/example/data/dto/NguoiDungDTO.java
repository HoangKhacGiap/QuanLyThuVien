package com.example.data.dto;

import com.example.data.entity.TaiKhoan;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class NguoiDungDTO {
    private Long id;
    private String tenNguoiDung;
    private String diaChi;
    private String dienThoai;
}
