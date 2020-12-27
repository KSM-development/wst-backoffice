package com.ksm.wstbackoffice.service;

import com.ksm.wstbackoffice.dto.WarehouseDto;
import com.ksm.wstbackoffice.entity.WarehouseEntity;
import com.ksm.wstbackoffice.mapper.WarehouseMapper;
import com.ksm.wstbackoffice.repository.WarehouseRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WarehouseService implements IWarehouseService {

    private WarehouseMapper warehouseMapper;
    private WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository, WarehouseMapper warehouseMapper) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseMapper = warehouseMapper;
    }

    public WarehouseDto save(WarehouseDto warehouseDto) {
        WarehouseEntity warehouseEntity = warehouseMapper.toEntity(warehouseDto);
        return warehouseMapper.toDto(warehouseRepository.save(warehouseEntity));
    }

    public List<WarehouseDto> findAll() {
        return warehouseMapper.toDtos(warehouseRepository.findAll());
    }

    public WarehouseDto findById(long id) {
        return warehouseMapper.toDto(warehouseRepository.findById(id).orElse(null));
    }

    public WarehouseDto update(WarehouseDto warehouseDto, long id) {
        WarehouseDto existWarehouse = findById(id);
        if (existWarehouse == null) {
            return null;
        }
        return save(warehouseDto);
    }
}
