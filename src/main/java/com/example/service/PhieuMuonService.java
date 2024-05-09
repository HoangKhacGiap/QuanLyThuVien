package com.example.service;

import com.example.data.dto.MessageResponse;
import com.example.data.dto.NguoiDungDTO;
import com.example.data.dto.PaginationDTO;
import com.example.data.dto.PhieuMuonDTO;

public interface PhieuMuonService {
    PaginationDTO filterPhieuMuon(String keyword, int pageNumber, int pageSize);
    MessageResponse addPhieuMuon();

}
