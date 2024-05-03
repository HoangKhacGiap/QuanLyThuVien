package com.example.data.repository;

import com.example.data.entity.TaiLieu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaiLieuRepository extends JpaRepository<TaiLieu,Long> {
    Optional<TaiLieu> findByTenTaiLieu(String tenTaiLieu);

}
