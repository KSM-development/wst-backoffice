package com.ksm.wstbackoffice.controller;

import com.ksm.wstbackoffice.entity.Address;
import com.ksm.wstbackoffice.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping(value = "addresses")
public class AddressController {
    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<Address>> findAll() {
        List<Address> addresses = addressService.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(addresses);
    }

    @GetMapping("{id}")
    public ResponseEntity<Address> findById(@PathVariable long id) {
        Address address = addressService.findById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(address);
    }

    @PostMapping
    public ResponseEntity<Address> create(@RequestBody Address entityAddress) {
        Address address = addressService.save(entityAddress);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(address);
    }
}
