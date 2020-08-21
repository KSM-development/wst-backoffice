package com.ksm.wstbackoffice.dto;

import lombok.Data;

@Data
public class AddressDto {
    private long id;
    private String countryISO3166;
    private String zipcode;
    private String region;
    private String district;
    private String city;
    private String street;
    private String houseNumber;
    private String apartmentNumber;
    private String description;
}
