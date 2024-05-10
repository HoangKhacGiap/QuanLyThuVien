package com.example.service.impl;

import com.example.data.dto.MessageResponse;
import com.example.data.dto.PaginationDTO;
import com.example.data.dto.PhieuMuonDTO;
import com.example.data.dto.PhieuTraSachDTO;
import com.example.data.entity.NguoiDung;
import com.example.data.entity.PhieuMuon;
import com.example.data.entity.PhieuTraSach;
import com.example.data.entity.TaiKhoan;
import com.example.data.mapper.PhieuTraSachMapper;
import com.example.data.repository.NguoiDungRepository;
import com.example.data.repository.PhieuMuonRepository;
import com.example.data.repository.PhieuTraSachRepository;
import com.example.data.repository.TaiKhoanRepository;
import com.example.enumeration.EConstantNumber;
import com.example.exception.ConflictException;
import com.example.exception.ExceptionCustom;
import com.example.exception.ResourceNotFoundException;
import com.example.service.PhieuTraSachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class PhieuTraSachServiceIpmpl implements PhieuTraSachService {
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;
    @Autowired
    private NguoiDungRepository nguoiDungRepository;
    @Autowired
    private PhieuTraSachRepository phieuTraSachRepository;
    @Autowired
    private PhieuTraSachMapper phieuTraSachMapper;
    @Autowired
    private PhieuMuonRepository phieuMuonRepository;
    @Override
    public PaginationDTO filterPhieuTraSach(String keyword, int pageNumber, int pageSize) {
        Page<PhieuTraSach> page = phieuTraSachRepository.filterPhieuTraSach(keyword, PageRequest.of(pageNumber, pageSize));
        List<PhieuTraSachDTO> list = new ArrayList<>();

        for (PhieuTraSach pts : page.getContent()) {

            PhieuTraSachDTO phieuTraSachDTO = phieuTraSachMapper.toDTO(pts);
//            phieuMuonDTO.setTenNguoiMuon(pm.getNguoiXacNhanPhieuMuon().getTenNguoiDung());
            list.add(phieuTraSachDTO);
        }

        return new PaginationDTO(list, page.isFirst(), page.isLast(),
                page.getTotalPages(), page.getTotalElements(), page.getNumber(), page.getSize());

    }

    @Override
    public MessageResponse createPhieuTraSach(long phieuMuonId) {
        NguoiDung nguoiDungHienTai = getNguoiDungByToken();
        PhieuMuon phieuMuon = phieuMuonRepository.findById(phieuMuonId).orElseThrow(
                () -> new ResourceNotFoundException(Collections.singletonMap("phieuMuonId: ", phieuMuonId))
        );
        if(phieuTraSachRepository.existsByPhieuMuon_Id(phieuMuonId)){
            throw new ExceptionCustom("phiêu mượn này đã trả",phieuMuonId);
        }
        if(!phieuMuon.isXacNhanMuon()){
            throw new ExceptionCustom("Phiếu mượn này chưa xác nhận mượn",phieuMuonId);
        }
        Date currentDate = new Date();
        PhieuTraSach createPhieuTraSach = new PhieuTraSach();

        long tienPhat = tinhTienPhat(phieuMuonId);

        createPhieuTraSach.setTienPhat(tienPhat);
        createPhieuTraSach.setNgayTra(currentDate);
        createPhieuTraSach.setPhieuMuon(phieuMuon);
        createPhieuTraSach.setNguoiMuonSach(nguoiDungHienTai);

        phieuTraSachRepository.save(createPhieuTraSach);
        return new MessageResponse(HttpServletResponse.SC_OK, "Tạo phiếu trả sách thành công");
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

    private long tinhTienPhat(long phieuMuonId){
        PhieuMuon phieuMuon = phieuMuonRepository.findById(phieuMuonId).orElseThrow(
                () -> new ResourceNotFoundException(Collections.singletonMap("phieuMuonId: ", phieuMuonId))
        );
        Date currentDate = new Date();
        long tienPhat = 0;
        long diffInMillies = Math.abs(currentDate.getTime() - phieuMuon.getHanMuon().getTime());
        long diffInDays = diffInMillies / (24 * 60 * 60 * 1000); // Chia cho số mili giây trong một ngày

        if(currentDate.getTime() > phieuMuon.getHanMuon().getTime()){
            tienPhat = diffInDays* EConstantNumber.tienPhat_MoiNgay;
        }
        return tienPhat;
    }
}
