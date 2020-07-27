package com.ksm.wstbackoffice.entity;

import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Country {
    @Id
    @Column(columnDefinition = "VARCHAR(3)")
    private String ISO3166;
    @Column(columnDefinition = "VARCHAR(255)")
    private String name;
}
