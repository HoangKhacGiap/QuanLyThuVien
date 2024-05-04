package com.example.service;

import com.example.data.dto.MessageResponse;
import com.example.data.dto.NhaCungCapDTO;
import com.example.data.dto.PaginationDTO;

public interface NhaCungCapService {
    MessageResponse createNhaCungCap(NhaCungCapDTO nhaCungCapDTO);
    PaginationDTO filterNhaCungCap(String keyword, int pageNumber, int pageSize);

}
