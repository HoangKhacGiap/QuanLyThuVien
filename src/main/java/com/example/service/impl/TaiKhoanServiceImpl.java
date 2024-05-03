package com.example.service.impl;

import com.example.data.dto.JwtResponseDTO;
import com.example.data.dto.LoginDTO;
import com.example.data.dto.MessageResponse;
import com.example.data.dto.RegisterDTO;
import com.example.data.entity.NguoiDung;
import com.example.data.entity.PhanQuyen;
import com.example.data.entity.TaiKhoan;
import com.example.data.repository.NguoiDungRepository;
import com.example.data.repository.PhanQuyenRepository;
import com.example.data.repository.TaiKhoanRepository;
import com.example.enumeration.ERole;
import com.example.exception.AccessDeniedException;
import com.example.exception.ConflictException;
import com.example.exception.ExceptionCustom;
import com.example.exception.ResourceNotFoundException;
import com.example.service.JwtService;
import com.example.service.TaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@Service
public class TaiKhoanServiceImpl implements TaiKhoanService {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TaiKhoanRepository taiKhoanRepository;
    @Autowired
    private PhanQuyenRepository phanQuyenRepository;
    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Override
    public JwtResponseDTO loginUser(LoginDTO loginDTO) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginDTO.getEmail());

        TaiKhoan taiKhoan = taiKhoanRepository.findByEmail(loginDTO.getEmail()).orElseThrow(
                () -> new ResourceNotFoundException(Collections.singletonMap("email: ", loginDTO.getEmail()))
        );

//        if (taiKhoan.isDeleted()) {
//            throw new AccessDeniedException(Collections.singletonMap("message", "user is deleted"));
//        }

        if (!checkValidPassword(loginDTO.getPassword(), taiKhoan.getMatKhau()))
            throw new AccessDeniedException(Collections.singletonMap("message", "password is wrong"));

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),
                        loginDTO.getPassword()));

//        SecurityContextHolder.getContext().setAuthentication(authentication);

        PhanQuyen phanQuyen = taiKhoan.getPhanQuyen();

        String jwt = jwtService.generateToken(userDetails);

        return new JwtResponseDTO(jwt, loginDTO.getEmail(), phanQuyen.getTenPhanQuyen());
    }

    @Override
    public MessageResponse createRegister(RegisterDTO registerDTO) {
        if (taiKhoanRepository.findByEmail(registerDTO.getEmail()).isPresent())
            throw new ConflictException(Collections.singletonMap("email", registerDTO.getEmail()));

        if (!registerDTO.getPassword().equals(registerDTO.getPasswordConfirm())) {
            throw new ExceptionCustom("Passwords do not match!");
        }

        if (registerDTO.getPhanQuyenId() == ERole.roleAdmin) {
            throw new ResourceNotFoundException(Collections.singletonMap("message", "admin access not allow"));
        } else if (registerDTO.getPhanQuyenId() == ERole.roleLibrary_manager) {
            throw new ResourceNotFoundException(Collections.singletonMap("message", "library manager access not allow"));
        } else if(registerDTO.getEmail()==null || registerDTO.getTenDangNhap()==null || registerDTO.getPassword()==null)
            throw new ExceptionCustom("Ban chua nhap day du thong tin");
        {

            PhanQuyen phanQuyen = phanQuyenRepository.findById(registerDTO.getPhanQuyenId()).orElseThrow(
                    () -> new ResourceNotFoundException(Collections.singletonMap("message", "role do not exist"))
            );

            TaiKhoan taiKhoan = new TaiKhoan();

            taiKhoan.setTenDangNhap(registerDTO.getTenDangNhap());
            taiKhoan.setEmail(registerDTO.getEmail());
            taiKhoan.setMatKhau(passwordEncoder.encode(registerDTO.getPassword()));
            taiKhoan.setPhanQuyen(phanQuyen);

            taiKhoanRepository.save(taiKhoan);

            //save id nguoi dung sau khi tao tai khoan
            if (registerDTO.getPhanQuyenId()==ERole.roleLecture || registerDTO.getPhanQuyenId()==ERole.roleStudent){
                TaiKhoan savedTaiKhoan = taiKhoanRepository.save(taiKhoan);
                NguoiDung nguoiDung = new NguoiDung();

                nguoiDung.setTenNguoiDung(registerDTO.getTenDangNhap());
                nguoiDung.setTaiKhoan(savedTaiKhoan);
                nguoiDungRepository.save(nguoiDung);
            }
        }
        return new MessageResponse(HttpServletResponse.SC_OK, "Tao user thanh cong");
    }

    private Boolean checkValidPassword(String password, String passwordEncoded) {

        return passwordEncoder.matches(password, passwordEncoded);
    }


}
