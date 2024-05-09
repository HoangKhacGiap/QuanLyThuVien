package com.example.data.repository;

import com.example.data.entity.NguoiDung;
import com.example.data.entity.TaiLieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TaiLieuRepository extends JpaRepository<TaiLieu,Long> {
    Optional<TaiLieu> findByTenTaiLieu(String tenTaiLieu);

    @Query("select tl from TaiLieu tl " +
            "where " +
            "(:keyword = '' " +
            "OR  tl.tenTaiLieu LIKE %:keyword% " +
            "OR  tl.tenTacGia LIKE %:keyword% " +
            "OR  tl.nhaXuatBan LIKE %:keyword% " +
            "OR  tl.ngonNgu LIKE %:keyword% " +
            "OR  tl.theLoai LIKE %:keyword%) " +

            "AND tl.xacNhanXoa = false " +
            "ORDER BY tl.id DESC")
    Page<TaiLieu> filterTaiLieu(@Param("keyword") String keyword, Pageable pageable);

}
