package com.example.data.mapper;

import com.example.data.dto.NguoiDungDTO;
import com.example.data.dto.PhieuMuonDTO;
import com.example.data.entity.PhieuMuon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface PhieuMuonNguoiDungMapper {
//    @Mapping(source = "nguoiDungDTO", target = "nguoiDungDTO")
//    PhieuMuonDTO toPhieuMuonDTO(NguoiDungDTO nguoiDungDTO);
//
//    @Mapping(source = "nguoiDungDTO", target = "nguoiDungDTO")
//    NguoiDungDTO toNguoiDungDTO(PhieuMuonDTO phieuMuonDTO);

    PhieuMuon toEntity(PhieuMuonDTO phieuMuonDTO);

    @Mapping(source ="nguoiXacNhanPhieuMuon", target = "nguoiDungDTO")
    PhieuMuonDTO toDTO(PhieuMuon phieuMuon);
}
