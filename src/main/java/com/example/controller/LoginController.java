package com.example.controller;


import com.example.data.dto.LoginDTO;
import com.example.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)//cors
@RestController
@RequestMapping("/api/login")
public class LoginController {
    @Autowired
    private TaiKhoanService taiKhoanService;

    @PostMapping("")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDTO loginDTO) {

        return ResponseEntity.ok(taiKhoanService.loginUser(loginDTO));
    }
}
