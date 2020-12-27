package com.ksm.wstbackoffice.controller;

import com.ksm.wstbackoffice.dto.WarehouseDto;
import com.ksm.wstbackoffice.service.IWarehouseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "warehouses")
public class WarehouseController {
    private IWarehouseService warehouseService;

    public WarehouseController(IWarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PostMapping
    public ResponseEntity<WarehouseDto> create(@RequestBody WarehouseDto warehouseDto) {
        WarehouseDto warehouse = warehouseService.save(warehouseDto);
        if (warehouse == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(warehouse);
    }

    @GetMapping
    public ResponseEntity<List<WarehouseDto>> findAll() {
        List<WarehouseDto> warehouses = warehouseService.findAll();
        return ResponseEntity.status(HttpStatus.OK)
                .body(warehouses);
    }

    @GetMapping("{id}")
    public ResponseEntity<WarehouseDto> findById(@PathVariable long id) {
        WarehouseDto warehouse = warehouseService.findById(id);
        if (warehouse == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(warehouse);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@RequestBody WarehouseDto warehouseDto, @PathVariable long id) {
        WarehouseDto warehouse = warehouseService.update(warehouseDto, id);
        if (warehouse == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(warehouse);
    }
}
