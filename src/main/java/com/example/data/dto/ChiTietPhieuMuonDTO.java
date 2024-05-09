package com.example.data.dto;

import com.example.data.entity.PhieuMuon;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class ChiTietPhieuMuonDTO {
    private long id;
    private TaiLieuDTO taiLieuDTO;
    private PhieuMuon phieuMuonDTO;
}
