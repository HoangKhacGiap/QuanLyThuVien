package com.example.data.repository;

import com.example.data.entity.NhaCungCap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NhaCungCapRepository extends JpaRepository<NhaCungCap,Long> {
    Optional<NhaCungCap> findByEmail(String email);

    Boolean existsByEmail(String email);
}
