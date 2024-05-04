package com.example.data.mapper;

import com.example.data.dto.NguoiDungDTO;
import com.example.data.entity.NguoiDung;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NguoiDungMapper {
//    @Mapping(target = "id", ignore = true)
    NguoiDung toEntity(NguoiDungDTO nguoiDungDTO);

    NguoiDungDTO toDTO(NguoiDung nguoiDung);
}
