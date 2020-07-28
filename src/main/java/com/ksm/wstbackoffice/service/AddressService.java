package com.ksm.wstbackoffice.service;

import com.ksm.wstbackoffice.entity.Address;
import com.ksm.wstbackoffice.repository.AddressRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AddressService {
    private AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Address findById(long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public Address save(Address address) {
        address = addressRepository.save(address);
        return address;
    }
}
