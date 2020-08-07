package com.ksm.wstbackoffice.controller;

import com.ksm.wstbackoffice.endpoint.EndPoint;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:sql/countryInsert.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:sql/countryDrop.sql")
})
@ActiveProfiles("test")
public class CountryControllerTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() throws Exception {
        RestAssured.port = port;
    }

    @Test
    public void findByIdTest() {
        given().
            pathParam("iso3166", "060").
        when().
            get(EndPoint.COUNTRY_ID).
        then().
            statusCode(200).
            body("iso3166", is("060"), "name", is("Bermuda"));
    }

    @Test
    public void findAllTest() {
        when().
            get(EndPoint.COUNTRIES).
        then().
            statusCode(200).
            body("isEmpty()", Matchers.is(false)).
            body("size()", equalTo(5));
    }
}
