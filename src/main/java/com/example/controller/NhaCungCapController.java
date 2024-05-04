package com.example.controller;

import com.example.data.dto.NhaCungCapDTO;
import com.example.data.dto.RegisterDTO;
import com.example.service.NhaCungCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class NhaCungCapController {
    @Autowired
    private NhaCungCapService nhaCungCapService;

    @PostMapping("/createNhaCungCap")
    public ResponseEntity<?> registerUser(@Valid @RequestBody NhaCungCapDTO nhaCungCapDTO) {
        return ResponseEntity.ok(nhaCungCapService.createNhaCungCap(nhaCungCapDTO));
    }

    @GetMapping("/filterNhaCungCap")
    public ResponseEntity<?> filterNhaCungCap(@RequestParam(defaultValue = "0") int pageNumber,
                                             @RequestParam(defaultValue = "10") int pageSize,
                                             @RequestParam(defaultValue = "") String keyword) {

        return ResponseEntity.ok(nhaCungCapService.filterNhaCungCap(keyword, pageNumber, pageSize));
    }
}
