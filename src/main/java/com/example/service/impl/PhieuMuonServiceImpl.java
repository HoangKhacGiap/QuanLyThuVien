package com.example.service.impl;

import com.example.data.dto.*;
import com.example.data.entity.NguoiDung;
import com.example.data.entity.PhieuMuon;
import com.example.data.entity.TaiKhoan;
import com.example.data.entity.TaiLieu;
import com.example.data.mapper.PhieuMuonNguoiDungMapper;
import com.example.data.repository.NguoiDungRepository;
import com.example.data.repository.PhieuMuonRepository;
import com.example.data.repository.TaiKhoanRepository;
import com.example.data.repository.TaiLieuRepository;
import com.example.enumeration.ERole;
import com.example.exception.ConflictException;
import com.example.exception.ExceptionCustom;
import com.example.exception.ResourceNotFoundException;
import com.example.service.PhieuMuonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.SecondaryTable;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PhieuMuonServiceImpl implements PhieuMuonService {
    @Autowired
    private PhieuMuonRepository phieuMuonRepository;
    @Autowired
    private PhieuMuonNguoiDungMapper phieuMuonNguoiDungMapper;
    @Autowired
    private NguoiDungRepository nguoiDungRepository;
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;
    @Override
    public PaginationDTO filterPhieuMuon(String keyword, int pageNumber, int pageSize) {
        Page<PhieuMuon> page = phieuMuonRepository.filterPhieuMuon(keyword, PageRequest.of(pageNumber, pageSize));
        List<PhieuMuonDTO> list = new ArrayList<>();

        for (PhieuMuon pm : page.getContent()) {

            PhieuMuonDTO phieuMuonDTO = phieuMuonNguoiDungMapper.toDTO(pm);
//            phieuMuonDTO.setTenNguoiMuon(pm.getNguoiXacNhanPhieuMuon().getTenNguoiDung());
            list.add(phieuMuonDTO);
        }

        return new PaginationDTO(list, page.isFirst(), page.isLast(),
                page.getTotalPages(), page.getTotalElements(), page.getNumber(), page.getSize());

    }

    @Override
    public MessageResponse addPhieuMuon() {
        NguoiDung nguoiDung = getNguoiDungByToken();
        if(phieuMuonRepository.countByCode(nguoiDung.getId()) >=2  && nguoiDung.getTaiKhoan().getPhanQuyen().getId() == ERole.roleStudent)
        {
            throw new ExceptionCustom("Sinh vien chi duoc tao 2 phieu muon");
        }

        if(phieuMuonRepository.countByCode(nguoiDung.getId()) >= 5 && nguoiDung.getTaiKhoan().getPhanQuyen().getId() == ERole.roleLecture)
        {
            throw new ExceptionCustom("Giao vien chi duoc tao 5 phieu muon");
        }

        PhieuMuon phieuMuon = new PhieuMuon();

        phieuMuon.setNguoiXacNhanPhieuMuon(nguoiDung);
        phieuMuon.setNgayMuon(null);
        phieuMuon.setNgayMuon(null);
        phieuMuon.setXacNhanMuon(false);

        phieuMuonRepository.save(phieuMuon);
            //        else {
//            TaiLieu taiLieu = taiLieuMapper.toEntity(taiLieuDTO);
//            taiLieuRepository.save(taiLieu);
//        }
        return new MessageResponse(HttpServletResponse.SC_OK, "Tạo phiếu mượn rỗng thành công");
    }

    @Override
    public List<PhieuMuonDTO> findPhieuMuonByAuthentication() {
        NguoiDung nguoiDung = getNguoiDungByToken();

        return phieuMuonRepository.findByNguoiXacNhanPhieuMuon_Id(nguoiDung.getId()).stream()
                .map(phieuMuon -> phieuMuonNguoiDungMapper.toDTO(phieuMuon)).collect(Collectors.toList());
    }

    @Override
    public MessageResponse updateXacNhanPhieuMuon(long phieuMuonId) {
        PhieuMuon phieuMuon = phieuMuonRepository.findById(phieuMuonId).orElseThrow(
                () -> new ResourceNotFoundException(Collections.singletonMap("phieuMuonId: ", phieuMuonId))
        );
        if(phieuMuon.isXacNhanMuon()){
            throw new ExceptionCustom("Phiếu này đã xác nhận rồi!!!");
        }
        Date currentDate = new Date();

        // Tạo một đối tượng Calendar và đặt thời gian hiện tại vào đó
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);

        // Thêm 7 ngày
        calendar.add(Calendar.DAY_OF_MONTH, 7);

        // Lấy ngày sau khi thêm 7 ngày
        Date futureDate = calendar.getTime();

//        PhieuMuon phieuMuonUpdate = phieuMuonRepository.findById(phieuMuonId);

//        phieuMuonUpdate.setId(phieuMuonId);
        phieuMuon.setNgayMuon(currentDate);
        phieuMuon.setHanMuon(futureDate);
        phieuMuon.setXacNhanMuon(true);

        phieuMuonRepository.save(phieuMuon);
        return new MessageResponse(HttpServletResponse.SC_OK, "Xác nhận muọn thành công");
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
