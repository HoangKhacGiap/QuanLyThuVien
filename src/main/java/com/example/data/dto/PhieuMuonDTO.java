package com.example.data.dto;

import lombok.*;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class PhieuMuonDTO {
    private long id;
    private Date ngayMuon;
    private Date hanMuon;
    private String xacNhanMuon;
//    private String tenNguoiMuon;
    private NguoiDungDTO nguoiDungDTO;
}
