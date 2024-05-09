package com.example.util;

import com.example.data.entity.PhanQuyen;
import com.example.data.entity.TaiKhoan;
import com.example.data.repository.PhanQuyenRepository;
import com.example.data.repository.TaiKhoanRepository;
import com.example.enumeration.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Init {
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;
    @Autowired
    private PhanQuyenRepository phanQuyenRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostConstruct
    void inIt() {
        inIt_Role();
        inIt_User();
    }

    private void inIt_Role() {
        for (ERole eRole : ERole.values()) {

            if (!phanQuyenRepository.existsByTenPhanQuyen(eRole.toString())) {
                PhanQuyen phanQuyen = new PhanQuyen();
                phanQuyen.setTenPhanQuyen(eRole.toString());

                phanQuyenRepository.save(phanQuyen);
            }
        }
    }

    private void inIt_User() {
        String email = "admin@gmail.com";
        String password = "123456";
        String tenDangNhap = "admin123";
        if (!taiKhoanRepository.existsByEmail(email)) {
            TaiKhoan taiKhoan = new TaiKhoan();

            taiKhoan.setTenDangNhap(tenDangNhap);
            taiKhoan.setEmail(email);
            taiKhoan.setMatKhau(passwordEncoder.encode(password));
            taiKhoan.setPhanQuyen(phanQuyenRepository.findById(ERole.roleAdmin).orElse(null));

            taiKhoanRepository.save(taiKhoan);
        }
        String emailThuThu = "thuthu1@gmail.com";
        String passwordThuThu = "123456";
        String tenDangNhapThuThu = "thuthu123";

        if (!taiKhoanRepository.existsByEmail(emailThuThu)) {
            TaiKhoan taiKhoan = new TaiKhoan();

            taiKhoan.setTenDangNhap(tenDangNhapThuThu);
            taiKhoan.setEmail(emailThuThu);
            taiKhoan.setMatKhau(passwordEncoder.encode(passwordThuThu));
            taiKhoan.setPhanQuyen(phanQuyenRepository.findById(ERole.roleLibrary_manager).orElse(null));

            taiKhoanRepository.save(taiKhoan);
        }
    }
}
