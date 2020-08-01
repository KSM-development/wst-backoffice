package com.ksm.wstbackoffice.controller;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class CountryControllerTest {
    @Value("${server.port}")
    private int port;

    @Test
    public void findByIdTest() {
        given()
                .baseUri(RestAssured.DEFAULT_URI)
                .port(port)
                .basePath("/countries/{iso3166}")
                .pathParam("iso3166", "061")
                .when()
                .get()
                .then()
                .body(
                        "iso3166", is("061")
                )
                .statusCode(200);
    }

    @Test
    public void findAllTest() {
        given()
                .baseUri(RestAssured.DEFAULT_URI)
                .port(port)
                .basePath("/countries")
                .when()
                .get()
                .then()
                .body("isEmpty()", Matchers.is(false))
                .statusCode(200);
    }
}
