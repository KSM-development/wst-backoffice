package com.ksm.wstbackoffice.service;

import com.ksm.wstbackoffice.dto.WarehouseDto;

import java.util.List;

public interface IWarehouseService {
    WarehouseDto save(WarehouseDto warehouseDto);
    List<WarehouseDto> findAll();
    WarehouseDto findById(long id);
    WarehouseDto update(WarehouseDto warehouseDto, long id);
}
