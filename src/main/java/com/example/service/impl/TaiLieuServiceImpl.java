package com.example.service.impl;

import com.example.data.dto.MessageResponse;
import com.example.data.dto.NguoiDungDTO;
import com.example.data.dto.PaginationDTO;
import com.example.data.dto.TaiLieuDTO;
import com.example.data.entity.NguoiDung;
import com.example.data.entity.NhaCungCap;
import com.example.data.entity.TaiLieu;
import com.example.data.mapper.TaiLieuMapper;
import com.example.data.repository.NhaCungCapRepository;
import com.example.data.repository.TaiLieuRepository;
import com.example.exception.ConflictException;
import com.example.exception.ResourceNotFoundException;
import com.example.service.TaiLieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
}
