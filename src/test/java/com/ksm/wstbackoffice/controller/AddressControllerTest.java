package com.ksm.wstbackoffice.controller;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@ActiveProfiles("test")
public class AddressControllerTest {
    @Value("${server.port}")
    private int port;

    @Test
    public void findByIdTest() {
        given()
                .baseUri(RestAssured.DEFAULT_URI)
                .port(port)
                .basePath("/addresses/{id}")
                .pathParam("id", 1)
                .when()
                .get()
                .then()
                .body(
                        "id", is(1)
                )
                .statusCode(200);
    }

    @Test
    public void findAllTest() {
        given()
                .baseUri(RestAssured.DEFAULT_URI)
                .port(port)
                .basePath("/addresses")
                .when()
                .get()
                .then()
                .body("isEmpty()", Matchers.is(false))
                .statusCode(200);
    }

    @Test
    public void addTest() {
        Map<String, Object> country = new HashMap<>();
        country.put("iso3166", "063");
        country.put("name", "Бермуды 063");

        Map<String, Object> address = new HashMap<>();
        address.put("zipcode", "zipcode1");
        address.put("region", "region1");
        address.put("district", "district1");
        address.put("city", "city1");
        address.put("street", "street1");
        address.put("houseNumber", "1a");
        address.put("apartmentNumber", "11");
        address.put("description", "description1");
        address.put("country", country);

        given()
                .contentType(JSON)
                .baseUri(RestAssured.DEFAULT_URI)
                .port(port)
                .basePath("/addresses")
                .body(address)
                .when()
                .post()
                .then()
                .statusCode(201);
    }
}
