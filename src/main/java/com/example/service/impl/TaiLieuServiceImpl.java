package com.example.service.impl;

import com.example.data.dto.MessageResponse;
import com.example.data.dto.NguoiDungDTO;
import com.example.data.dto.PaginationDTO;
import com.example.data.dto.TaiLieuDTO;
import com.example.data.entity.*;
import com.example.data.mapper.TaiLieuMapper;
import com.example.data.repository.*;
import com.example.enumeration.ERole;
import com.example.exception.ConflictException;
import com.example.exception.ExceptionCustom;
import com.example.exception.ResourceNotFoundException;
import com.example.service.TaiLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TaiLieuServiceImpl implements TaiLieuService {

    @Autowired
    private TaiLieuService taiLieuService;
    @Autowired
    private TaiLieuRepository taiLieuRepository;
    @Autowired
    private TaiLieuMapper taiLieuMapper;

    @Autowired
    private PhieuMuonRepository phieuMuonRepository;

    @Autowired
    private NguoiDungRepository nguoiDungRepository;
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;

    @Autowired
    private ChiTietPhieuMuonRepository chiTietPhieuMuonRepository;

    @Override
    public MessageResponse createTaiLieu(TaiLieuDTO taiLieuDTO) {
        if (taiLieuRepository.findByTenTaiLieu(taiLieuDTO.getTenTaiLieu()).isPresent())
            throw new ConflictException(Collections.singletonMap("tenTaiLieu", taiLieuDTO.getTenTaiLieu()));
//        else if (nhaCungCapRepository.findByDienThoai(nhaCungCapDTO.getDienThoai()).isPresent()) {
//            throw new ConflictException(Collections.singletonMap("dienThoai", nhaCungCapDTO.getDienThoai()));
        else {
            TaiLieu taiLieu = taiLieuMapper.toEntity(taiLieuDTO);
            taiLieuRepository.save(taiLieu);
        }
        return new MessageResponse(HttpServletResponse.SC_OK, "Tao TaiLieu thanh cong");
    }

    @Override
    public MessageResponse addTaiLieutoPhieuMuon(long phieuMuonId, long taiLieuId) {
        PhieuMuon phieuMuon = phieuMuonRepository.findById(phieuMuonId).orElseThrow(
                () -> new ResourceNotFoundException(Collections.singletonMap("phieu muon id: ", phieuMuonId))
        );
        TaiLieu taiLieu = taiLieuRepository.findById(taiLieuId).orElseThrow(
                () -> new ResourceNotFoundException(Collections.singletonMap("TaiLieu_id : ", taiLieuId))
        );
        NguoiDung nguoiDung = getNguoiDungByToken();
        if(nguoiDung.getTaiKhoan().getPhanQuyen().getId() == ERole.roleStudent
                && phieuMuonRepository.countByTaiLieuMuon(nguoiDung.getId()) >= 2){
            throw new ExceptionCustom("sinh vien chi duoc muon 2 tai lieu");
        }

        if(nguoiDung.getTaiKhoan().getPhanQuyen().getId() == ERole.roleLecture
                && phieuMuonRepository.countByTaiLieuMuon(nguoiDung.getId()) >= 5){
            throw new ExceptionCustom("giang vien chi duoc muon 5 tai lieu");
        }

            ChiTietPhieuMuon createChiTietPhieuMuon = new ChiTietPhieuMuon();
            createChiTietPhieuMuon.setPhieuMuon(phieuMuon);
            createChiTietPhieuMuon.setTaiLieu(taiLieu);
            chiTietPhieuMuonRepository.save(createChiTietPhieuMuon);

        return new MessageResponse(HttpServletResponse.SC_OK, "Them tai lieu vao phieu thanh cong");
    }

    @Override
    public PaginationDTO filterTaiLieu(String keyword, int pageNumber, int pageSize) {
        Page<TaiLieu> page = taiLieuRepository.filterTaiLieu(keyword, PageRequest.of(pageNumber, pageSize));
        List<TaiLieuDTO> list = new ArrayList<>();

        for (TaiLieu tl : page.getContent()) {

            TaiLieuDTO taiLieuDTO = taiLieuMapper.toDTO(tl);

            list.add(taiLieuDTO);
        }

        return new PaginationDTO(list, page.isFirst(), page.isLast(),
                page.getTotalPages(), page.getTotalElements(), page.getNumber(), page.getSize());

    }

    @Override
    public TaiLieuDTO findTaiLieuById(long id) {
        TaiLieu taiLieu = taiLieuRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(Collections.singletonMap("exam id:", id))
        );

        TaiLieuDTO taiLieuDTO = taiLieuMapper.toDTO(taiLieu);

        return taiLieuDTO;
    }

    public NguoiDung getNguoiDungByToken() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        TaiKhoan taiKhoan = taiKhoanRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException(Collections.singletonMap("message", "Tai khoan nay khong ton tai")));

        NguoiDung nguoiDung = nguoiDungRepository.findByTaiKhoanId(taiKhoan.getId()).orElseThrow(
                () -> new ResourceNotFoundException(Collections.singletonMap("message", "nguoi dung nay khong ton tai"))
        );

        return nguoiDung;
    }
}
