package com.ksm.wstbackoffice.repository;

import com.ksm.wstbackoffice.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {
}
