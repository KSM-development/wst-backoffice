package com.ksm.wstbackoffice.mapper;

import com.ksm.wstbackoffice.dto.CountryDto;
import com.ksm.wstbackoffice.entity.CountryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryDto toDto(CountryEntity countryEntity);
    List<CountryDto> toDTOs(List<CountryEntity> countryEntities);
    CountryEntity toEntity(CountryDto countryDto);
}
