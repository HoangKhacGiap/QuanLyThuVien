package com.example.controller;

import com.example.data.dto.NguoiDungDTO;
import com.example.data.dto.NhaCungCapDTO;
import com.example.service.NguoiDungService;
import com.example.service.NhaCungCapService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/NguoiDung")
public class NguoiDungController {
    @Autowired
    private NguoiDungService nguoiDungService;

    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/updateNguoiDung")
    public ResponseEntity<?> registerUser(@Valid @RequestBody NguoiDungDTO nguoiDungDTO) {
        return ResponseEntity.ok(nguoiDungService.updateNguoiDung(nguoiDungDTO));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/GetNguoiDung")
    public  ResponseEntity<?> getNguoiDungHienTai(){
        return ResponseEntity.ok(nguoiDungService.getNguoiDung());
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filterNguoiDung(@RequestParam(defaultValue = "0") int pageNumber,
                                            @RequestParam(defaultValue = "10") int pageSize,
                                            @RequestParam(defaultValue = "") String keyword) {

        return ResponseEntity.ok(nguoiDungService.filterNguoiDung(keyword, pageNumber, pageSize));
    }
}
