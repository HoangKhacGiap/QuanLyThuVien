package com.example.data.repository;

import com.example.data.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan,Long> {
//    Optional<TaiKhoan> findById(Long aLong);

    Optional<TaiKhoan> findByEmail(String email);

    Boolean existsByEmail(String email);

}
