package com.ksm.wstbackoffice.entity;

import lombok.Data;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name="country_iso3166")
    private CountryEntity country;
    @Column(columnDefinition = "VARCHAR(15)")
    private String zipcode;
    @Column(columnDefinition = "VARCHAR(50)")
    private String region;
    @Column(columnDefinition = "VARCHAR(50)")
    private String district;
    @Column(columnDefinition = "VARCHAR(50)")
    private String city;
    @Column(columnDefinition = "VARCHAR(50)")
    private String street;
    @Column(columnDefinition = "VARCHAR(10)")
    private String houseNumber;
    @Column(columnDefinition = "VARCHAR(10)")
    private String apartmentNumber;
    @Column(columnDefinition = "TEXT")
    private String description;
}
