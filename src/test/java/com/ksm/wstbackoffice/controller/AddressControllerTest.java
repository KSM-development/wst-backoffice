package com.ksm.wstbackoffice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class AddressControllerTest {
    @Value("${server.port}")
    private int port;

    @Value("${server.address}")
    private String address;

    @Value("${local.protocol}")
    private String protocol;

    @Test
    public void findByIdTest() {
        given()
                .accept(JSON)
                .pathParam("id", 1)
                .when()
                .get(String.valueOf(new StringBuilder()
                        .append(protocol)
                        .append(address)
                        .append(":")
                        .append(port)
                        .append("/addresses/{id}")))
                .then()
                .statusCode(200)
                .body(
                        "id", is(1)
                );
    }

    @Test
    public void findAllTest() {
        given()
                .when()
                .get(String.valueOf(new StringBuilder()
                        .append(protocol)
                        .append(address)
                        .append(":")
                        .append(port)
                        .append("/addresses")))
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    public void addTest() {
        Map<String, Object> country = new HashMap<>();
        country.put("iso3166", "068");
        country.put("name", "Бермуды 068");

        Map<String, Object> mapAddress = new HashMap<>();
        mapAddress.put("zipcode", "zipcode1");
        mapAddress.put("region", "region1");
        mapAddress.put("district", "district1");
        mapAddress.put("city", "city1");
        mapAddress.put("street", "street1");
        mapAddress.put("houseNumber", "1a");
        mapAddress.put("apartmentNumber", "11");
        mapAddress.put("description", "description1");
        mapAddress.put("country", country);

        given()
                .contentType(JSON)
                .body(mapAddress)
                .when()
                .post(String.valueOf(new StringBuilder()
                        .append(protocol)
                        .append(address)
                        .append(":")
                        .append(port)
                        .append("/addresses")))
                .then()
                .statusCode(201)
                .log()
                .all();
    }
}
