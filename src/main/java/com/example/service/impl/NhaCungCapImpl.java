package com.example.service.impl;

import com.example.data.dto.MessageResponse;
import com.example.data.dto.NguoiDungDTO;
import com.example.data.dto.NhaCungCapDTO;
import com.example.data.dto.PaginationDTO;
import com.example.data.entity.NguoiDung;
import com.example.data.entity.NhaCungCap;
import com.example.data.mapper.NhaCungCapMapper;
import com.example.data.repository.NhaCungCapRepository;
import com.example.exception.ConflictException;
import com.example.service.NhaCungCapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class NhaCungCapImpl implements NhaCungCapService {
    @Autowired
    private NhaCungCapRepository nhaCungCapRepository;
    @Autowired
    private NhaCungCapMapper nhaCungCapMapper;
    @Override
    public MessageResponse createNhaCungCap(NhaCungCapDTO nhaCungCapDTO) {
        if (nhaCungCapRepository.findByEmail(nhaCungCapDTO.getEmail()).isPresent())
            throw new ConflictException(Collections.singletonMap("email", nhaCungCapDTO.getEmail()));
        else if (nhaCungCapRepository.findByDienThoai(nhaCungCapDTO.getDienThoai()).isPresent()) {
            throw new ConflictException(Collections.singletonMap("dienThoai", nhaCungCapDTO.getDienThoai()));
        } else {
            NhaCungCap nhaCungCap = nhaCungCapMapper.toEntity(nhaCungCapDTO);
            nhaCungCapRepository.save(nhaCungCap);
        }
        return new MessageResponse(HttpServletResponse.SC_OK, "Tao NhaCungCap thanh cong");
    }

    @Override
    public PaginationDTO filterNhaCungCap(String keyword, int pageNumber, int pageSize) {
        Page<NhaCungCap> page = nhaCungCapRepository.filterNhaCungCap(keyword, PageRequest.of(pageNumber, pageSize));
        List<NhaCungCapDTO> list = new ArrayList<>();

        for (NhaCungCap nhaCungCap: page.getContent()) {

            NhaCungCapDTO nhaCungCapDTO = nhaCungCapMapper.toDTO(nhaCungCap);

            list.add(nhaCungCapDTO);
        }
        return new PaginationDTO(list, page.isFirst(), page.isLast(),
                page.getTotalPages(), page.getTotalElements(), page.getNumber(), page.getSize());

    }
}
