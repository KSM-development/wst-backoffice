package com.ksm.wstbackoffice.domain;

import com.ksm.wstbackoffice.enums.PriceType;
import com.ksm.wstbackoffice.enums.WarehouseType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "warehouses")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Enumerated(EnumType.STRING)
    @Column(name = "warehouse_type", length = 9)
    private WarehouseType warehouseType;
    @Enumerated(EnumType.STRING)
    @Column(name = "price_type", length = 9)
    private PriceType priceType;
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
