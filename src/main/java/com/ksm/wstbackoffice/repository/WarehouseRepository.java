package com.ksm.wstbackoffice.repository;

import com.ksm.wstbackoffice.entity.WarehouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Long> {
}
