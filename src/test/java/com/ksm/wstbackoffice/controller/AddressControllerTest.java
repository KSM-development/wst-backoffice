package com.ksm.wstbackoffice.controller;

import com.ksm.wstbackoffice.endpoint.EndPoint;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
public class AddressControllerTest {

    private static RequestSpecification spec;

    public AddressControllerTest() {
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
            pathParam("id", 1).
        when().
            get(EndPoint.ADDRESSES_ID).
        then().
            statusCode(200).
            body("id", equalTo(1)).
            body("country.iso3166", equalTo("804"));
    }

    @Test
    public void findAllTest() {
        given().
            spec(spec).
        when().
            get(EndPoint.ADDRESSES).
        then().
            statusCode(200).
            body("isEmpty()", Matchers.is(false)).
            body("size()", equalTo(4));
    }

    @Test
    public void createTest() {
        Map<String, Object> country = new HashMap<>();
        country.put("iso3166", "076");
        country.put("name", "Brazil");

        Map<String, Object> address = new HashMap<>();
        address.put("zipcode", "13010");
        address.put("region", "Brazil region");
        address.put("district", "Brazil district");
        address.put("city", "Rio de Janeiro");
        address.put("street", "Rio Carnival");
        address.put("houseNumber", "8a");
        address.put("apartmentNumber", "89");
        address.put("description", "Brazil address description");
        address.put("country", country);

        given().
            spec(spec).
            body(address).
        when().
            post(EndPoint.ADDRESSES).
        then().
            statusCode(201).
            body("id", equalTo(5));
    }
}
