package com.ksm.wstbackoffice.mapper;

import com.ksm.wstbackoffice.dto.AddressDto;
import com.ksm.wstbackoffice.entity.AddressEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(source = "country.ISO3166", target = "countryISO3166")
    AddressDto toDto(AddressEntity entity);

    List<AddressDto> toDtos(List<AddressEntity> entities);

    AddressEntity toEntity(AddressDto dto);
}
