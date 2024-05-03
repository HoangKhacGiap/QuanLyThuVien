package com.example.service.impl;

import com.example.data.dto.MessageResponse;
import com.example.data.dto.TaiLieuDTO;
import com.example.data.entity.NhaCungCap;
import com.example.data.entity.TaiLieu;
import com.example.data.mapper.TaiLieuMapper;
import com.example.data.repository.NhaCungCapRepository;
import com.example.data.repository.TaiLieuRepository;
import com.example.exception.ConflictException;
import com.example.service.TaiLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@Service
public class TaiLieuServiceImpl implements TaiLieuService {

    @Autowired
    private TaiLieuService taiLieuService;
    @Autowired
    private TaiLieuRepository taiLieuRepository;
    @Autowired
    private TaiLieuMapper taiLieuMapper;

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
}
