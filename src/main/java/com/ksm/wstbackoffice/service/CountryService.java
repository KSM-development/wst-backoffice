package com.ksm.wstbackoffice.service;

import com.ksm.wstbackoffice.dto.CountryDto;
import com.ksm.wstbackoffice.mapper.CountryMapper;
import com.ksm.wstbackoffice.repository.CountryRepository;
import com.ksm.wstbackoffice.validation.ValidationConstant;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
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

    public Map<String, Map<String, ?>> findAllBy(Collection<String> nameStartsWithFilters) {
        Map<String, Map<String, ?>> countriesByNameStartingWith = new HashMap<String, Map<String, ?>>();
        Map<String, Collection<String>> detailsCountriesByNameStartingWith = new HashMap<String, Collection<String>>();
        Map<String, Integer> totalCountriesByNameStartingWith = new HashMap<String, Integer>();

        List<CountryDto> countryDtoList = findAll();

        nameStartsWithFilters.stream()
                .filter(p -> p.trim().length() > 0)
                .forEach(strFilter -> {
                    Pattern pattern = Pattern.compile(ValidationConstant.FILTER_NAME_REGEX.concat(strFilter.toLowerCase().trim()));
                    Set<String> filteredCountries = countryDtoList.stream()
                            .filter(f -> pattern.matcher(f.getName().toLowerCase()).find())
                            .map(CountryDto::getName)
                            .collect(Collectors.toSet());
                    totalCountriesByNameStartingWith.put(strFilter, filteredCountries.size());
                    detailsCountriesByNameStartingWith.put(strFilter, filteredCountries);
                });

        countriesByNameStartingWith.put("total", totalCountriesByNameStartingWith);
        countriesByNameStartingWith.put("details", detailsCountriesByNameStartingWith);

        return countriesByNameStartingWith;
    }
}
