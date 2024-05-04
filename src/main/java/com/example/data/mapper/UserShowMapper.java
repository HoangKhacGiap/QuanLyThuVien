package com.example.data.mapper;

import com.example.data.dto.NguoiDungDTO;
import com.example.data.dto.TaiLieuDTO;
import com.example.data.entity.TaiKhoan;
import com.example.data.entity.TaiLieu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserShowMapper {
    @Mapping(target = "id", ignore = true)
    TaiKhoan toEntity(NguoiDungDTO nguoiDungDTO);


}
