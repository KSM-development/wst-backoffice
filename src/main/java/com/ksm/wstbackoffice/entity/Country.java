package com.ksm.wstbackoffice.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country")
@Data
public class Country {
    @Id
    @Column(name = "iso3166", columnDefinition = "VARCHAR(3)")
    private String ISO3166;
    @Column(name = "name", columnDefinition = "VARCHAR(255)")
    private String name;
}
