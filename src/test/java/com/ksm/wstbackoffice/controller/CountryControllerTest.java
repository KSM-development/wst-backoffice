package com.ksm.wstbackoffice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CountryControllerTest {
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
                .pathParam("iso3166", "065")
                .when()
                .get(String.valueOf(new StringBuilder()
                        .append(protocol)
                        .append(address)
                        .append(":")
                        .append(port)
                        .append("/countries/{iso3166}")))
                .then()
                .statusCode(200)
                .body(
                        "iso3166", is("065")
                )
                .log()
                .all();
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
                        .append("/countries")))
                .then()
                .statusCode(200)
                .log()
                .all();
    }
}
