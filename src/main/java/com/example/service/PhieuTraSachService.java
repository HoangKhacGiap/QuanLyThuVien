package com.example.service;

import com.example.data.dto.MessageResponse;
import com.example.data.dto.PaginationDTO;

public interface PhieuTraSachService {
    PaginationDTO filterPhieuTraSach(String keyword, int pageNumber, int pageSize);
    MessageResponse createPhieuTraSach(long phieuMuonId);
}
