package com.example.data.repository;

import com.example.data.entity.ChiTietPhieuMuon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ChiTietPhieuMuonRepository extends JpaRepository<ChiTietPhieuMuon,Long> {
    @Override
    Optional<ChiTietPhieuMuon> findById(Long id);
}
