package com.example.service;

import com.example.data.dto.JwtResponseDTO;
import com.example.data.dto.LoginDTO;
import com.example.data.dto.MessageResponse;
import com.example.data.dto.RegisterDTO;

public interface TaiKhoanService {
    JwtResponseDTO loginUser(LoginDTO loginDTO);

    MessageResponse createRegister(RegisterDTO registerDTO);
}
