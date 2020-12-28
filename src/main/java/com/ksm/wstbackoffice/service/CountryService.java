package com.ksm.wstbackoffice.service;

import com.ksm.wstbackoffice.dto.CountryDto;
import com.ksm.wstbackoffice.mapper.CountryMapper;
import com.ksm.wstbackoffice.repository.CountryRepository;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        return countryMapper.toDto(countryRepository.findById(id).orElse(null));
    }

    public Map<String, List<String>> startWith(List<String> stringsToStartWith) {
        Map<String, List<String>> stringListMap = new HashMap<String, List<String>>();

        List<CountryDto> countryDtoList = findAll();

        stringsToStartWith.stream().filter(p -> p.trim().length() > 0)
                .forEach(e -> {
                    List<String> listFilteredCountries = countryDtoList.stream()
                            .filter(f -> f.getName().toLowerCase().startsWith(e.trim()))
                            .map(CountryDto::getName)
                            .collect(Collectors.toList());
                    stringListMap.put(e, listFilteredCountries);
                });

        return stringListMap;
    }
}
