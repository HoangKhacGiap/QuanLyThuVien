package com.example.controller;

import com.example.data.dto.PhieuMuonDTO;
import com.example.data.repository.PhieuTraSachRepository;
import com.example.service.PhieuTraSachService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PhieuTraSachController {
    @Autowired
    private PhieuTraSachService phieuTraSachService;
    @GetMapping("/filterPhieuTraSach")
    public ResponseEntity<?> filterPhieuTraSach(@RequestParam(defaultValue = "0") int pageNumber,
                                                @RequestParam(defaultValue = "10") int pageSize,
                                                @RequestParam(defaultValue = "") String keyword) {
        return ResponseEntity.ok(phieuTraSachService.filterPhieuTraSach(keyword, pageNumber,pageSize));
    }
    @PostMapping("/addPhieuTraSach/phieuMuonId={phieuMuonId}")
    @PreAuthorize("isAuthenticated()")
    @SecurityRequirement(name = "Bearer Authentication")
    public ResponseEntity<?> addPhieuTraSach(@PathVariable Long phieuMuonId) {
        return ResponseEntity.ok(phieuTraSachService.createPhieuTraSach(phieuMuonId));
    }
}
