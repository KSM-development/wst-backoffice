package com.ksm.wstbackoffice.controller;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:sql/addressInsert.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:sql/addressDelete.sql")
})
public class AddressControllerIT extends BaseControllerIT {
    @Test
    public void findByIdTest() {
        given().
            pathParam("id", 1).
        when().
            get("addresses/{id}").
        then().
            statusCode(200).
            body("id", equalTo(1)).
            body("country.iso3166", equalTo("804"));
    }

    @Test
    public void findAllTest() {
        when().
            get("addresses").
        then().
            statusCode(200).
            body("isEmpty()", Matchers.is(false));
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
            contentType(ContentType.JSON).
            body(address).
        when().
            post("addresses").
        then().
            statusCode(201).
            body("id", notNullValue()).
            body("region", equalTo("Brazil region")).
            body("district", equalTo("Brazil district")).
            body("city", equalTo("Rio de Janeiro"));

    }
}
