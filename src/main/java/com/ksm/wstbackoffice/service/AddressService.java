package com.ksm.wstbackoffice.service;

import com.ksm.wstbackoffice.dto.AddressDto;
import com.ksm.wstbackoffice.dto.CountryDto;
import com.ksm.wstbackoffice.entity.AddressEntity;
import com.ksm.wstbackoffice.mapper.AddressMapper;
import com.ksm.wstbackoffice.repository.AddressRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AddressService {
    private AddressRepository addressRepository;
    private AddressMapper addressMapper;
    private CountryService countryService;

    public AddressService(AddressRepository addressRepository,
                          AddressMapper addressMapper,
                          CountryService countryService) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.countryService = countryService;
    }

    public List<AddressDto> findAll() {
        return addressMapper.toDtos(addressRepository.findAll());
    }

    public AddressDto findById(long id) {
        return addressMapper.toDto(addressRepository.findById(id).orElse(null));
    }

    public AddressDto save(AddressDto addressDto) {
        CountryDto country = countryService.findById(addressDto.getCountryAlpha3code());
        if (country == null) {
            return null;
        }
        AddressEntity addressEntity = addressMapper.toEntity(addressDto);
        return addressMapper.toDto(addressRepository.save(addressEntity));
    }
}
