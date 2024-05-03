package com.example.data.mapper;

import com.example.data.dto.NhaCungCapDTO;
import com.example.data.entity.NhaCungCap;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NhaCungCapMapper {
    @Mapping(target = "id", ignore = true)
    NhaCungCap toEntity(NhaCungCapDTO nhaCungCapDTO);

    NhaCungCapDTO toDTO(NhaCungCap question);
}
