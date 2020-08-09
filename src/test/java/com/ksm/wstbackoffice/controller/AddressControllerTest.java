package com.ksm.wstbackoffice.controller;

import com.ksm.wstbackoffice.endpoint.EndPoint;
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

@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:sql/addressInsert.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:sql/addressDrop.sql")
})
public class AddressControllerTest extends BaseController {
    @Test
    public void findByIdTest() {
        given().
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
            contentType(ContentType.JSON).
            body(address).
        when().
            post(EndPoint.ADDRESSES).
        then().
            statusCode(201).
            body("id", equalTo(5));
    }
}
