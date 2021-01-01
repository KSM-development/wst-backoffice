package com.ksm.wstbackoffice.service;

import com.ksm.wstbackoffice.dto.CountryDto;
import com.ksm.wstbackoffice.mapper.CountryMapper;
import com.ksm.wstbackoffice.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public Map<String, Map<String, ?>> startWith(Collection<String> nameStartsWithFilters) {
        Map<String, Map<String, ?>> countriesByNameStartingWith = new HashMap<String, Map<String, ?>>();
        Map<String, Collection<String>> detailsCountriesByNameStartingWith = new HashMap<String, Collection<String>>();
        Map<String, Long> totalCountriesByNameStartingWith = new HashMap<String, Long>();

        List<CountryDto> countryDtoList = findAll();

        nameStartsWithFilters.stream()
                .filter(p -> p.trim().length() > 0)
                .forEach(p -> {
                    Set<String> filteredCountries = new HashSet();
                    countryDtoList.forEach(f -> {
                        if (f.getName().toLowerCase().startsWith(p.toLowerCase().trim())) {
                            String name = f.getName();
                            filteredCountries.add(name);
                        } else if (f.getName().toLowerCase().split(" ").length > 1) {
                            Arrays.stream(f.getName().split(" "))
                                    .filter(e -> e.toLowerCase().startsWith(p.toLowerCase().trim()))
                                    .map(e -> f.getName()).forEach(filteredCountries::add);
                        }
                    });
                    totalCountriesByNameStartingWith.put(p, filteredCountries.stream().count());
                    detailsCountriesByNameStartingWith.put(p, filteredCountries);
                });

        countriesByNameStartingWith.put("total", totalCountriesByNameStartingWith);
        countriesByNameStartingWith.put("details", detailsCountriesByNameStartingWith);

        return countriesByNameStartingWith;
    }
}
