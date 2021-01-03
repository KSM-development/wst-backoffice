package com.ksm.wstbackoffice.service;

import com.ksm.wstbackoffice.dto.CountryDto;
import com.ksm.wstbackoffice.exception.ResourceNotFoundException;
import com.ksm.wstbackoffice.mapper.CountryMapper;
import com.ksm.wstbackoffice.repository.CountryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CountryService {
    private CountryRepository countryRepository;
    private CountryMapper countryMapper;

    public CountryService(CountryRepository countryRepository, CountryMapper countryMapper) {
        this.countryRepository = countryRepository;
        this.countryMapper = countryMapper;
    }

    public List<CountryDto> findAll() {
        return countryMapper.toDtos(countryRepository.findAll());
    }

    public CountryDto findById(String id) {
        return countryMapper.toDto(countryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Not found country with id %s ", id))));
    }
}
