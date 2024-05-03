package com.example.data.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class TaiLieuDTO {
    private Long id;
    private String tenTaiLieu;
    private String tenTacGia;
    private String ngonNgu;
    private String theLoai;
    private String nhaXuatBan;
    private String noiDung;
    private int soLuongTon;
    private String hinhAnhDaiDien;


}
