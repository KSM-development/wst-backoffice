package com.ksm.wstbackoffice.mapper;

import com.ksm.wstbackoffice.dto.WarehouseDto;
import com.ksm.wstbackoffice.entity.WarehouseEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    WarehouseDto toDto(WarehouseEntity entity);

    WarehouseEntity toEntity(WarehouseDto dto);

    List<WarehouseDto> toDtos(List<WarehouseEntity> entities);
}
