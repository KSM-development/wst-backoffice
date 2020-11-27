package com.ksm.wstbackoffice.mapper;

import com.ksm.wstbackoffice.dto.AddressDto;
import com.ksm.wstbackoffice.entity.AddressEntity;
import com.ksm.wstbackoffice.repository.CountryRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class AddressMapper {
    @Autowired
    protected CountryRepository countryRepository;

    @Mapping(source = "country.alpha3code", target = "countryAlpha3code")
    public abstract AddressDto toDto(AddressEntity entity);

    public abstract List<AddressDto> toDtos(List<AddressEntity> entities);

    @Mapping(target="country", expression="java(countryRepository.getOne(dto.getCountryAlpha3code()))")
    public abstract AddressEntity toEntity(AddressDto dto);
}
