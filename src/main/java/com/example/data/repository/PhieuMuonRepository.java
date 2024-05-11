package com.example.data.repository;

import com.example.data.entity.PhieuMuon;
import com.example.data.entity.TaiLieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PhieuMuonRepository extends JpaRepository<PhieuMuon,Long> {
    Optional<PhieuMuon> findById(long id);
    List<PhieuMuon> findByNguoiXacNhanPhieuMuon_Id(long id);

    @Query("select count(*) from PhieuMuon  " +
            "where nguoiXacNhanPhieuMuon.id =:codeInput " +
            "and xacNhanMuon=false")
    int countByCode(long codeInput);

    @Query("select pm from PhieuMuon pm " +
            "left join NguoiDung n on pm.nguoiXacNhanPhieuMuon.id = n.id " +
            "where " +
            "(:keyword = '' " +
            "OR  n.tenNguoiDung LIKE %:keyword% )" +
//            "OR  tl.tenTacGia LIKE %:keyword% " +
//            "OR  tl.nhaXuatBan LIKE %:keyword% " +
//            "OR  tl.ngonNgu LIKE %:keyword% " +
//            "OR  tl.theLoai LIKE %:keyword%) " +
//            "AND pm.xacNhanXoa = false " +
            "ORDER BY pm.id DESC")
    Page<PhieuMuon> filterPhieuMuon(@Param("keyword") String keyword, Pageable pageable);

//    Object filterPhieuMuon(String keyword, int pageNumber, int pageSize);


    @Query("select count(c.taiLieu.id) from PhieuMuon pm " +
            "join ChiTietPhieuMuon c on c.phieuMuon.id = pm.id " +
            "where pm.nguoiXacNhanPhieuMuon.id =:codeInput " +
            "and pm.xacNhanMuon=false")
    int countByTaiLieuMuon(long codeInput);



    @Query("select pm from PhieuMuon pm " +
            "join NguoiDung n on pm.nguoiXacNhanPhieuMuon.id = n.id " +
            "where " +
            "(:keyword = '' " +
            "OR  n.tenNguoiDung LIKE %:keyword% )" +
//            "OR  tl.tenTacGia LIKE %:keyword% " +
//            "OR  tl.nhaXuatBan LIKE %:keyword% " +
//            "OR  tl.ngonNgu LIKE %:keyword% " +
//            "OR  tl.theLoai LIKE %:keyword%) " +
//            "AND pm.xacNhanXoa = false " +
            "ORDER BY pm.id DESC")
    Page<PhieuMuon> filterPhieuMuonChuaTra(@Param("keyword") String keyword, Pageable pageable);

}
