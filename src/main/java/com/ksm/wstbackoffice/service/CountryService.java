package com.ksm.wstbackoffice.service;

import com.ksm.wstbackoffice.entity.Country;
import com.ksm.wstbackoffice.repository.CountryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CountryService {
    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }
}
