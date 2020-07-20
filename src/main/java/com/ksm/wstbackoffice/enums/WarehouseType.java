package com.ksm.wstbackoffice.enums;

public enum WarehouseType {
    RETAIL("Retail"),
    WHOLESALE("Wholesale");

    private String value;

    WarehouseType(String value) {
        this.value = value;
    }
}
