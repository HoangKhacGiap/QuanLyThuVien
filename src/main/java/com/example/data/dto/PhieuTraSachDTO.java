package com.example.data.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class PhieuTraSachDTO {
    private long id;
    private Date ngayTra;
    private long tienPhat;
    private NguoiDungDTO thuThuXacNhanPhieuTra;
    private PhieuMuonDTO phieuMuonDTO;
}
