package com.ksm.wstbackoffice.repository;

import com.ksm.wstbackoffice.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, String> {
}
