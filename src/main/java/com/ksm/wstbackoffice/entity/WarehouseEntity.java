package com.ksm.wstbackoffice.entity;

import com.ksm.wstbackoffice.enumeration.PriceType;
import com.ksm.wstbackoffice.enumeration.WarehouseType;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "warehouse")
public class WarehouseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "warehouse_sequence")
    @SequenceGenerator(name = "warehouse_sequence",
            sequenceName = "warehouse_sequence", allocationSize = 1)
    private Long id;
    @Column(columnDefinition = "VARCHAR(255)")
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "warehouse_type")
    private WarehouseType warehouseType;
    @Enumerated(EnumType.STRING)
    @Column(name = "price_type")
    private PriceType priceType;
    @Column(columnDefinition = "TEXT")
    private String description;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "address_id")
    private AddressEntity address;
}
