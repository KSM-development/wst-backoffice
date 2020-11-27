package com.ksm.wstbackoffice.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "country")
public class CountryEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(3)")
    private String alpha3code;
    @Column(columnDefinition = "VARCHAR(2)")
    private String alpha2code;
    @Column(columnDefinition = "VARCHAR(255)")
    private String name;
}
