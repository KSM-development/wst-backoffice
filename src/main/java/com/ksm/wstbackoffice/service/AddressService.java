package com.ksm.wstbackoffice.service;

import com.ksm.wstbackoffice.dto.AddressDto;
import com.ksm.wstbackoffice.entity.AddressEntity;
import com.ksm.wstbackoffice.mapper.AddressMapper;
import com.ksm.wstbackoffice.repository.AddressRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AddressService {
    private AddressRepository addressRepository;
    private AddressMapper addressMapper;

    public AddressService(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    public List<AddressDto> findAll() {
        return addressMapper.toDTOs(addressRepository.findAll());
    }

    public AddressDto findById(long id) {
        return addressMapper.toDto(addressRepository.findById(id).orElse(null));
    }

    public AddressDto save(AddressDto addressDto) {
        AddressEntity addressEntity = addressMapper.toEntity(addressDto);
        return addressMapper.toDto(addressRepository.save(addressEntity));
    }
}
