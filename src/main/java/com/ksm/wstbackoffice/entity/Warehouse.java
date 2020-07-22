package com.ksm.wstbackoffice.entity;

import com.ksm.wstbackoffice.enumeration.PriceType;
import com.ksm.wstbackoffice.enumeration.WarehouseType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Warehouse {
    private Long id;
    private String title;
    private WarehouseType warehouseType;
    private PriceType priceType;
    private String description;
    private Address address;
}
