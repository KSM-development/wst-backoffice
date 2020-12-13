package com.ksm.wstbackoffice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AddressDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long id;
    private String countryAlpha3code;
    private String zipcode;
    private String region;
    private String district;
    private String city;
    private String street;
    private String houseNumber;
    private String apartmentNumber;
    private String description;
}
