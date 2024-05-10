package com.example.data.repository;

import com.example.data.entity.PhieuTraSach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PhieuTraSachRepository extends JpaRepository<PhieuTraSach, Long> {
    @Override
    Optional<PhieuTraSach> findById(Long id);
    boolean findByNguoiMuonSach_Id(long id);

    boolean existsByPhieuMuon_Id(long id);

    @Query("select pm from PhieuTraSach pm " +
            "left join NguoiDung n on pm.nguoiMuonSach.id = n.id " +
            "where " +
            "(:keyword = '' " +
            "OR  n.tenNguoiDung LIKE %:keyword% )" +
//            "OR  tl.tenTacGia LIKE %:keyword% " +
//            "OR  tl.nhaXuatBan LIKE %:keyword% " +
//            "OR  tl.ngonNgu LIKE %:keyword% " +
//            "OR  tl.theLoai LIKE %:keyword%) " +
//            "AND pm.xacNhanXoa = false " +
            "ORDER BY pm.id DESC")
    Page<PhieuTraSach> filterPhieuTraSach(@Param("keyword") String keyword, Pageable pageable);

}
