package com.example.data.repository;

import com.example.data.entity.NguoiDung;
import com.example.data.entity.TaiKhoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NguoiDungRepository extends JpaRepository<NguoiDung,Long> {
    @Override
    Optional<NguoiDung> findById(Long id);
    Optional<NguoiDung> findByTaiKhoanId(Long taiKhoanId);
    Optional<NguoiDung> findByTenNguoiDung(String tenNguoiDung);

    @Query("select n from NguoiDung n " +
            "left join TaiKhoan t on n.taiKhoan.id = t.id " +
            "where " +
            "(:keyword = '' " +
            "OR  n.tenNguoiDung LIKE %:keyword% " +
            "OR  n.diaChi LIKE %:keyword% " +
            "OR  n.dienThoai LIKE %:keyword%) " +

            "AND t.phanQuyen.id != 1 " +
            "AND t.phanQuyen.id != 2 " +
            "AND t.xacNhanXoa = false " +
            "ORDER BY t.id DESC")
    Page<NguoiDung> filterNguoiDung(@Param("keyword") String keyword, Pageable pageable);

}
