package com.ksm.wstbackoffice.controller;

import com.ksm.wstbackoffice.dto.AddressDto;
import com.ksm.wstbackoffice.dto.WarehouseDto;
import com.ksm.wstbackoffice.entity.AddressEntity;
import com.ksm.wstbackoffice.enumeration.PriceType;
import com.ksm.wstbackoffice.enumeration.WarehouseType;
import com.ksm.wstbackoffice.mapper.AddressMapper;
import com.ksm.wstbackoffice.service.AddressService;
import com.ksm.wstbackoffice.service.WarehouseService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql/warehouseInsert.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:sql/warehouseDelete.sql")
})
public class WarehouseControllerIT extends BaseControllerIT {
    @Autowired
    private WarehouseService warehouseService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressMapper addressMapper;


    public static Stream<Arguments> findByIdTestArguments() {
        return Stream.of(
                Arguments.of(-1L, 200),
                Arguments.of(-100L, 404),
                Arguments.of(0L, 404),
                Arguments.of(Long.MAX_VALUE, 404)
        );
    }

    @ParameterizedTest
    @MethodSource("findByIdTestArguments")
    public void findByIdTest(Long id, int expectedStatus) {
        given().
            pathParam("id", id).
        when().
            get("warehouses/{id}").
        then().
            statusCode(expectedStatus);
    }

    @Test
    public void findAllTest() {
        when().
            get("warehouses").
        then().
            statusCode(200).
        body("size()", equalTo(4));
    }

    @Test
    public void createTest() {
        AddressEntity address = new AddressEntity();
        address.setApartmentNumber("9");
        address.setCity("Kiev");
        address.setDistrict("Podil district");
        address.setHouseNumber("14a");
        address.setRegion("Podil");
        address.setStreet("Sagaidachnogo");
        address.setZipcode("01032");
        address.setDescription("description address");

        WarehouseDto warehouseDto = new WarehouseDto();
        warehouseDto.setName("warehause_5");
        warehouseDto.setWarehouseType(WarehouseType.RETAIL);
        warehouseDto.setPriceType(PriceType.RETAIL);
        warehouseDto.setDescription("description_5");
        warehouseDto.setAddress(address);

        given().
            contentType(ContentType.JSON).
            body(warehouseDto).
        when().
            post("warehouses").
        then().
            statusCode(201).
            body("id", notNullValue()).
            body("name", equalTo(warehouseDto.getName())).
            body("warehouseType", equalTo(warehouseDto.getWarehouseType().name())).
            body("priceType", equalTo(warehouseDto.getPriceType().name()));
    }

    @Test
    public void updateTest() {
        int warehouse_id = -1;
        int address_id = -4;

        AddressDto addressDto = addressService.findById(address_id);

        WarehouseDto warehouseDto = warehouseService.findById(warehouse_id);
        warehouseDto.setName("warehouse_update_1");
        warehouseDto.setPriceType(PriceType.WHOLESALE);
        warehouseDto.setWarehouseType(WarehouseType.WHOLESALE);
        warehouseDto.setDescription("warehouse_update_description_1");
        warehouseDto.setAddress(addressMapper.toEntity(addressDto));

        given().
            contentType(ContentType.JSON).
            body(warehouseDto).
            pathParam("id", warehouse_id).
        when().
            put("warehouses/{id}").
        then().
            statusCode(200).
            body("id", notNullValue()).
            body("name", equalTo(warehouseDto.getName())).
            body("warehouseType", equalTo(warehouseDto.getWarehouseType().name())).
            body("priceType", equalTo(warehouseDto.getPriceType().name())).
            body("address.id", equalTo(address_id));
    }
}
