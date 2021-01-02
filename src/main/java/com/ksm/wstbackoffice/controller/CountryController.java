package com.ksm.wstbackoffice.controller;

import com.ksm.wstbackoffice.dto.CountryDto;
import com.ksm.wstbackoffice.service.CountryService;
import com.ksm.wstbackoffice.validation.ValidationConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = "countries")
public class CountryController {
    private CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<List<CountryDto>> findAll() {
        List<CountryDto> countries = countryService.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(countries);
    }

    @GetMapping("{id}")
    public ResponseEntity<CountryDto> findById(@PathVariable String id) {
        if (!Pattern.matches(ValidationConstant.ONLY_LETTERS_LENGTH_3_REGEX, id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        CountryDto country = countryService.findById(id);

        if (country == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(country);
    }

    @GetMapping("/filter-name")
    public ResponseEntity<Map<String, Map<String, ?>>> findAllBy(@RequestBody Collection<String> nameStartsWithFilters) {
        if (nameStartsWithFilters.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Map<String, Map<String, ?>> filteredCountries = countryService.findAllBy(nameStartsWithFilters);

        return ResponseEntity.status(HttpStatus.OK)
                .body(filteredCountries);
    }
}
