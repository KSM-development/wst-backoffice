package com.ksm.wstbackoffice.entity;

import com.ksm.wstbackoffice.enumeration.PriceType;
import com.ksm.wstbackoffice.enumeration.WarehouseType;
import lombok.Data;

@Data
public class Warehouse {
    private Long id;
    private String name;
    private WarehouseType warehouseType;
    private PriceType priceType;
    private String description;
    private Address address;
}
