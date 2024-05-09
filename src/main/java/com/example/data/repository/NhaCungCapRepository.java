package com.example.data.repository;

import com.example.data.entity.NguoiDung;
import com.example.data.entity.NhaCungCap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NhaCungCapRepository extends JpaRepository<NhaCungCap,Long> {
    Optional<NhaCungCap> findByEmail(String email);
    Optional<NhaCungCap> findByDienThoai(String dienThoai);
    Boolean existsByEmail(String email);
    @Query("select ncc from NhaCungCap ncc " +
//            "left join TaiKhoan t on n.taiKhoan.id = t.id " +
            "where " +
            "(:keyword = '' " +
            "OR  ncc.tenNhaCungCap LIKE %:keyword% " +
            "OR  ncc.email LIKE %:keyword% " +
            "OR  ncc.diaChi LIKE %:keyword% " +
            "OR  ncc.dienThoai LIKE %:keyword%) " +

//            "AND t.phanQuyen.id != 1 " +
//            "AND t.phanQuyen.id != 2 " +
//            "AND t.xacNhanXoa = false " +
            "ORDER BY ncc.id DESC")
    Page<NhaCungCap> filterNhaCungCap(@Param("keyword") String keyword, Pageable pageable);

}
