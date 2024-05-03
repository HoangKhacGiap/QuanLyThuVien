package com.example.data.mapper;

import com.example.data.dto.TaiLieuDTO;
import com.example.data.entity.TaiLieu;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaiLieuMapper {
    @Mapping(target = "id", ignore = true)
    TaiLieu toEntity(TaiLieuDTO taiLieuDTO);

    TaiLieuDTO toDTO(TaiLieu question);
}
