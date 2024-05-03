package com.example.data.repository;

import com.example.data.entity.PhanQuyen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhanQuyenRepository extends JpaRepository<PhanQuyen,Long>{
    Boolean existsByTenPhanQuyen(String tenPhanQuyen);
}
