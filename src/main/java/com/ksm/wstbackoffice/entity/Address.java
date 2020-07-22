package com.ksm.wstbackoffice.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Address {
    private Country country;
    private City city;
    private String street;
    private String zipCode;
    private String buildingNumber;
    private String state;
}
