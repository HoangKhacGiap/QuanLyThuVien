package com.example.service;

import com.example.data.dto.MessageResponse;
import com.example.data.dto.TaiLieuDTO;

public interface TaiLieuService {
    MessageResponse createTaiLieu(TaiLieuDTO taiLieuDTO);

}