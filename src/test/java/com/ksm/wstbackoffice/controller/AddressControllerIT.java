package com.ksm.wstbackoffice.controller;

import com.ksm.wstbackoffice.dto.AddressDto;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:sql/addressInsert.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:sql/addressDelete.sql")
})
public class AddressControllerIT extends BaseControllerIT {
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
            get("addresses/{id}").
        then().
            statusCode(expectedStatus);
    }

    @Test
    public void findAllTest() {
        when().
            get("addresses").
        then().
            statusCode(200).
            body("size()", CoreMatchers.equalTo(4));
    }

    @Test
    public void createWithoutCountryTest() {
        Map<String, Object> address = new HashMap<>();
        address.put("zipcode", "13010");
        address.put("region", "Brazil region");
        address.put("district", "Brazil district");
        address.put("city", "Rio de Janeiro");
        address.put("street", "Rio Carnival");
        address.put("houseNumber", "8a");
        address.put("apartmentNumber", "89");
        address.put("description", "Brazil address description");

        given().
            contentType(ContentType.JSON).
            body(address).
        when().
            post("addresses").
        then().
            statusCode(400);
    }

    @Test
    public void createWithIncorrectFormatCountryTest() {
        Map<String, Object> address = new HashMap<>();
        address.put("zipcode", "13010");
        address.put("region", "Brazil region");
        address.put("district", "Brazil district");
        address.put("city", "Rio de Janeiro");
        address.put("street", "Rio Carnival");
        address.put("houseNumber", "8a");
        address.put("apartmentNumber", "89");
        address.put("description", "Brazil address description");
        address.put("countryAlpha3code", "BRA1");

        given().
            contentType(ContentType.JSON).
            body(address).
        when().
            post("addresses").
        then().
            statusCode(400);
    }

    @Test
    public void createWithNotExistentCountryTest() {
        Map<String, Object> address = new HashMap<>();
        address.put("zipcode", "13010");
        address.put("region", "Brazil region");
        address.put("district", "Brazil district");
        address.put("city", "Rio de Janeiro");
        address.put("street", "Rio Carnival");
        address.put("houseNumber", "8a");
        address.put("apartmentNumber", "89");
        address.put("description", "Brazil address description");
        address.put("countryAlpha3code", "XXX");

        given().
            contentType(ContentType.JSON).
            body(address).
        when().
            post("addresses").
        then().
            statusCode(400);
    }

    @Test
    public void createTest() {
        AddressDto addressDto = new AddressDto();
        addressDto.setZipcode("13010");
        addressDto.setRegion("Brazil region");
        addressDto.setDistrict("Brazil district");
        addressDto.setCity("Rio de Janeiro");
        addressDto.setStreet("Rio Carnival");
        addressDto.setHouseNumber("8a");
        addressDto.setApartmentNumber("89");
        addressDto.setDescription("Brazil address description");
        addressDto.setCountryAlpha3code("AUS");


        given().
            contentType(ContentType.JSON).
            body(addressDto).
        when().
            post("addresses").
        then().
            statusCode(201).
            body("id", notNullValue()).
            body("region", equalTo("Brazil region")).
            body("district", equalTo("Brazil district")).
            body("city", equalTo("Rio de Janeiro")).
            body("countryAlpha3code", equalTo("AUS"));
    }
}
