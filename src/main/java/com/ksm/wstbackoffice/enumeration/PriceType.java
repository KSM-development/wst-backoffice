package com.ksm.wstbackoffice.enumeration;

import lombok.Getter;

@Getter
public enum PriceType {
    RETAIL("Retail"),
    WHOLESALE("Wholesale");

    private String value;

    PriceType(String value) {
        this.value = value;
    }
}
