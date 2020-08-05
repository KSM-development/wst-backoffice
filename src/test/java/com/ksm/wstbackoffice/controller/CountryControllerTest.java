package com.ksm.wstbackoffice.controller;

import com.ksm.wstbackoffice.endpoint.EndPoint;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CountryControllerTest {

    private static RequestSpecification spec;

    public CountryControllerTest() {
        spec = new RequestSpecBuilder().
                setContentType(JSON).
                setBaseUri(RestAssured.DEFAULT_URI).
                setPort(EndPoint.PORT).
                addFilter(new ResponseLoggingFilter()).
                addFilter(new RequestLoggingFilter()).
                build();
    }

    @Test
    public void findByIdTest() {
        given().
            spec(spec).
            pathParam("iso3166", "060").
        when().
            get(EndPoint.COUNTRY_ID).
        then().
            statusCode(200).
            body("iso3166", is("060"), "name", is("Bermuda"));
    }

    @Test
    public void findAllTest() {
        given().
            spec(spec).
        when().
            get(EndPoint.COUNTRIES).
        then().
            statusCode(200).
            body("isEmpty()", Matchers.is(false)).
            body("size()", equalTo(5));
    }
}
