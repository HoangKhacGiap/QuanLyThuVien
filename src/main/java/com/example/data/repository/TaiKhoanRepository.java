package com.example.data.repository;

import com.example.data.entity.TaiKhoan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TaiKhoanRepository extends JpaRepository<TaiKhoan,Long> {
//    Optional<TaiKhoan> findById(Long aLong);

    Optional<TaiKhoan> findByEmail(String email);
    Optional<TaiKhoan> findByTenDangNhap(String tenDangNhap);
    Boolean existsByEmail(String email);

//    @Query("select t from TaiKhoan t " +
//            "left join NguoiDung n on t.id = n.taiKhoan.id " +
//            "where " +
//            "(:keyword = '' " +
//            "OR  t.email LIKE %:keyword%) " +
//            "AND(t.phanQuyen.id = :roleId or :roleId = 0 ) " +
//            "AND t.phanQuyen.id != 1 " +
//            "AND t.phanQuyen.id != 2 " +
//            "AND t.xacNhanXoa = false " +
//            "ORDER BY t.id DESC")
//    Page<TaiKhoan> filterTaiKhoan(@Param("keyword") String keyword, @Param("roleId") long roleId, Pageable pageable);

}
