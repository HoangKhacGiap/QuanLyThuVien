package com.example.service;

import com.example.data.dto.ChiTietPhieuMuonDTO;
import com.example.data.dto.MessageResponse;
import com.example.data.dto.PaginationDTO;
import com.example.data.dto.TaiLieuDTO;

public interface TaiLieuService {
    MessageResponse createTaiLieu(TaiLieuDTO taiLieuDTO);

    MessageResponse addTaiLieutoPhieuMuon(long phieuMuonId, long taiLieuId);

    PaginationDTO filterTaiLieu(String keyword, int pageNumber, int pageSize);

    TaiLieuDTO findTaiLieuById(long id);

}
