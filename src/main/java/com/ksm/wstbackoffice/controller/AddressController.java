package com.ksm.wstbackoffice.controller;

import com.ksm.wstbackoffice.dto.AddressDto;
import com.ksm.wstbackoffice.service.AddressService;
import com.ksm.wstbackoffice.validation.ValidationConstant;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.regex.Pattern;

@RestController
@RequestMapping(value = "addresses")
public class AddressController {
    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> findAll() {
        List<AddressDto> addresses = addressService.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(addresses);
    }

    @GetMapping("{id}")
    public ResponseEntity<AddressDto> findById(@PathVariable long id) {
        AddressDto address = addressService.findById(id);

        if (address == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(address);
    }

    @PostMapping
    public ResponseEntity<AddressDto> create(@RequestBody AddressDto addressDto) {
        String countryAlpha3code = addressDto.getCountryAlpha3code();
        if (countryAlpha3code == null || !Pattern.matches(ValidationConstant.ONLY_LETTERS_LENGTH_3_REGEX, countryAlpha3code)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        AddressDto address = addressService.save(addressDto);
        if (address == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(address);
    }
}
