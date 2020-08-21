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

    @Mapping(source = "country.ISO3166", target = "countryISO3166")
    public abstract AddressDto toDto(AddressEntity entity);

    public abstract List<AddressDto> toDtos(List<AddressEntity> entities);

    @Mapping(target="country", expression="java(countryRepository.getOne(dto.getCountryISO3166()))")
    public abstract AddressEntity toEntity(AddressDto dto);
}
