package com.ksm.wstbackoffice.entity;

import lombok.Data;

@Data
public class Address {
    private long id;
    private Country country;
    private String zipcode;
    private String region;
    private String district;
    private String city;
    private String street;
    private String houseNumber;
    private String apartmentNumber;
    private String description;
}
