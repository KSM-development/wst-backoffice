package com.ksm.wstbackoffice.enumeration;

import lombok.Getter;

@Getter
public enum WarehouseType {
    RETAIL("Retail"),
    WHOLESALE("Wholesale");

    private String value;

    WarehouseType(String value) {
        this.value = value;
    }
}
