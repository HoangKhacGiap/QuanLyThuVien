package com.example.service;

import com.example.data.dto.*;

public interface TaiKhoanService {
    JwtResponseDTO loginUser(LoginDTO loginDTO);

    MessageResponse createRegister(RegisterDTO registerDTO);

//    PaginationDTO filterTaiKhoan(String keyword, long roleId, int pageNumber, int pageSize);

}
