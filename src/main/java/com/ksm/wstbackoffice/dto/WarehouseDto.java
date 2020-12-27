package com.ksm.wstbackoffice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ksm.wstbackoffice.entity.AddressEntity;
import com.ksm.wstbackoffice.enumeration.PriceType;
import com.ksm.wstbackoffice.enumeration.WarehouseType;
import lombok.Data;

@Data
public class WarehouseDto {
    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Long id;
    private String name;
    private WarehouseType warehouseType;
    private PriceType priceType;
    private String description;
    private AddressEntity address;
}
