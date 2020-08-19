package com.ksm.wstbackoffice.mapper;

import com.ksm.wstbackoffice.dto.CountryDto;
import com.ksm.wstbackoffice.entity.CountryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryDto toDto(CountryEntity entity);

    List<CountryDto> toDtos(List<CountryEntity> entities);

    CountryEntity toEntity(CountryDto dto);
}
