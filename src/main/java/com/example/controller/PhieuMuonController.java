package com.example.controller;

import com.example.data.dto.NguoiDungDTO;
import com.example.data.dto.PhieuMuonDTO;
import com.example.data.dto.TaiLieuDTO;
import com.example.data.repository.PhieuMuonRepository;
import com.example.service.PhieuMuonService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PhieuMuonController {
    @Autowired
    private PhieuMuonService phieuMuonService;

    @GetMapping("/filterPhieuMuon")
    public ResponseEntity<?> filterNguoiDung(@RequestParam(defaultValue = "0") int pageNumber,
                                             @RequestParam(defaultValue = "10") int pageSize,
                                             @RequestParam(defaultValue = "") String keyword) {

        return ResponseEntity.ok(phieuMuonService.filterPhieuMuon(keyword, pageNumber,pageSize));
    }

    @PostMapping("/addPhieuMuon")
    public ResponseEntity<?> addPhieuMuon(@Valid PhieuMuonDTO phieuMuonDTO) {
        return ResponseEntity.ok(phieuMuonService.addPhieuMuon());
    }

    @GetMapping("/getPhieuMuonAuthentication")
    public ResponseEntity<?> findPhieuMuonByAuthentication() {

        return ResponseEntity.ok(phieuMuonService.findPhieuMuonByAuthentication());
    }

//    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping("/xacNhanPhieuMuon/phieuMuonId={id}")
    public ResponseEntity<?> xacNhanPhieuMuon( @PathVariable Long id) {
        return ResponseEntity.ok(phieuMuonService.updateXacNhanPhieuMuon(id));
    }
}
