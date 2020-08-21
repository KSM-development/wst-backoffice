package com.ksm.wstbackoffice.entity;

import com.ksm.wstbackoffice.enumeration.PriceType;
import com.ksm.wstbackoffice.enumeration.WarehouseType;
import lombok.Data;

import javax.persistence.Table;

@Data
@Table(name = "warehous")
public class WarehouseEntity {
    private Long id;
    private String name;
    private WarehouseType warehouseType;
    private PriceType priceType;
    private String description;
    private AddressEntity address;
}
