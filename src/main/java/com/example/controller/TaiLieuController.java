package com.example.controller;

import com.example.data.dto.TaiLieuDTO;
import com.example.service.TaiLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TaiLieuController {
    @Autowired
    private TaiLieuService taiLieuService;

    @PostMapping("/createTaiLieu")
    public ResponseEntity<?> registerUser(@Valid @RequestBody TaiLieuDTO taiLieuDTO) {
        return ResponseEntity.ok(taiLieuService.createTaiLieu(taiLieuDTO));
    }

    @GetMapping("/filterTaiLieu")
    public ResponseEntity<?> filterNguoiDung(@RequestParam(defaultValue = "0") int pageNumber,
                                             @RequestParam(defaultValue = "10") int pageSize,
                                             @RequestParam(defaultValue = "") String keyword) {

        return ResponseEntity.ok(taiLieuService.filterTaiLieu(keyword, pageNumber, pageSize));
    }

    @GetMapping("/taiLieu_id={id}")
    public ResponseEntity<?> findExamById(@PathVariable long id) {

        return ResponseEntity.ok(taiLieuService.findTaiLieuById(id));
    }
}
