package com.example.data.mapper;

import com.example.data.dto.PhieuTraSachDTO;
import com.example.data.entity.PhieuTraSach;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface PhieuTraSachMapper {
    PhieuTraSach toEntity(PhieuTraSachDTO phieuTraSachDTO);
//    @Mapping(source ="thuThuXacNhanPhieuTra.nguoiXacNhanPhieuMuon", target = "phieuMuonDTO.nguoiDungDTO")
    @Mapping(source ="nguoiMuonSach", target = "thuThuXacNhanPhieuTra")
    @Mapping(source ="phieuMuon.nguoiXacNhanPhieuMuon", target = "phieuMuonDTO.nguoiDungDTO")
    PhieuTraSachDTO toDTO(PhieuTraSach phieuTraSach);
}
