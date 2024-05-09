package com.example.service.impl;

import com.example.data.dto.MessageResponse;
import com.example.data.dto.NguoiDungDTO;
import com.example.data.dto.PaginationDTO;
import com.example.data.entity.NguoiDung;
import com.example.data.entity.TaiKhoan;
import com.example.data.mapper.NguoiDungMapper;
import com.example.data.repository.NguoiDungRepository;
import com.example.data.repository.TaiKhoanRepository;
import com.example.exception.ResourceNotFoundException;
import com.example.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class NguoiDungServiceImpl implements NguoiDungService {
    @Autowired
    private NguoiDungService nguoiDungService;
    @Autowired
    private NguoiDungRepository nguoiDungRepository;
    @Autowired
    private TaiKhoanRepository taiKhoanRepository;
    @Autowired
    private NguoiDungMapper nguoiDungMapper;
    @Override
    public MessageResponse updateNguoiDung( NguoiDungDTO nguoiDungDTO) {
        NguoiDung nguoiDung = getNguoiDungByToken();

        NguoiDung nguoiDungupdate = nguoiDungMapper.toEntity(nguoiDungDTO);

        nguoiDungupdate.setId(nguoiDung.getId());
        nguoiDungupdate.setTaiKhoan(nguoiDung.getTaiKhoan());

        nguoiDungRepository.save(nguoiDungupdate);
        return new MessageResponse(HttpServletResponse.SC_OK, "update thanh cong");
    }

    @Override
    public Optional<NguoiDung> getNguoiDung() {
        NguoiDung nguoiDung = getNguoiDungByToken();

        Optional<NguoiDung> getNguoiDung = nguoiDungRepository.findById(nguoiDung.getId());
        return getNguoiDung;
    }

    @Override
    public PaginationDTO filterNguoiDung(String keyword, int pageNumber, int pageSize) {
        Page<NguoiDung> page = nguoiDungRepository.filterNguoiDung(keyword, PageRequest.of(pageNumber, pageSize));
        List<NguoiDungDTO> list = new ArrayList<>();

        for (NguoiDung user : page.getContent()) {

            NguoiDungDTO nguoiDungDTO = nguoiDungMapper.toDTO(user);

            list.add(nguoiDungDTO);
        }

        return new PaginationDTO(list, page.isFirst(), page.isLast(),
                page.getTotalPages(), page.getTotalElements(), page.getNumber(), page.getSize());

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
