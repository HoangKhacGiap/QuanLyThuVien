package com.example.service;

import com.example.data.dto.MessageResponse;
import com.example.data.dto.NguoiDungDTO;
import com.example.data.dto.PaginationDTO;
import com.example.data.entity.NguoiDung;

import java.util.Optional;

public interface NguoiDungService {
    MessageResponse updateNguoiDung(NguoiDungDTO nguoiDungDTO);
    Optional<NguoiDung> getNguoiDung();


    PaginationDTO filterNguoiDung(String keyword, int pageNumber, int pageSize);

}
