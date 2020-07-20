package com.ksm.wstbackoffice.enums;

public enum PriceType {
    RETAIL("Retail"),
    WHOLESALE("Wholesale");

    private String value;

    PriceType(String value) {
        this.value = value;
    }
}
