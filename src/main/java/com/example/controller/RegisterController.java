package com.example.controller;

import com.example.data.dto.RegisterDTO;
import com.example.data.entity.TaiKhoan;
import com.example.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/register")
public class RegisterController {
    @Autowired
    private TaiKhoanService taiKhoanService;

    @PostMapping("")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterDTO registerDTO) {


        return ResponseEntity.ok(taiKhoanService.createRegister(registerDTO));
    }
}
