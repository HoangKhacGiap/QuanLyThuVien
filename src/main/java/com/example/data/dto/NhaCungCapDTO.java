package com.example.data.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class NhaCungCapDTO {
    private Long id;

    @NotNull(message = "ten nhaCungCap khong duoc de null")
    @NotEmpty(message = "ten nhaCungCap khong duoc de trong")
    private String tenNhaCungCap;

    @Email(message = "email khong dung dinh dang")
    @NotNull(message = "email khong duoc de null")
    @NotEmpty(message = "email khong duoc de trong")
    private String email;

    @NotNull(message = "dia chi khong duoc de null")
    @NotEmpty(message = "dia chi khong duoc de trong")
    private String diaChi;

    @NotNull(message = "dien thoai khong duoc de null")
    private String dienThoai;
}
