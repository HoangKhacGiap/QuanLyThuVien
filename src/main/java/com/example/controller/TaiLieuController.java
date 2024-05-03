package com.example.controller;

import com.example.data.dto.TaiLieuDTO;
import com.example.service.TaiLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/createTaiLieu")
public class TaiLieuController {
    @Autowired
    private TaiLieuService taiLieuService;

    @PostMapping("")
    public ResponseEntity<?> registerUser(@Valid @RequestBody TaiLieuDTO taiLieuDTO) {
        return ResponseEntity.ok(taiLieuService.createTaiLieu(taiLieuDTO));
    }
}
