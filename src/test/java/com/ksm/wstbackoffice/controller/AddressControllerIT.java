package com.ksm.wstbackoffice.controller;

import com.ksm.wstbackoffice.exception.CheckValueException;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:sql/addressInsert.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:sql/addressDelete.sql")
})
public class AddressControllerIT extends BaseControllerIT {
    @ParameterizedTest
    @CsvSource({
            ",",
            "0"
    })
    public void findByIdinvalidValuesAreNotAllowedTest(Long id) {
        Throwable thrown = catchThrowable(() -> findByIdTest(id));
        assertThat(thrown)
                .isInstanceOf(CheckValueException.class)
                .hasMessageContaining("id is mandatory");
    }

    @ParameterizedTest
    @CsvSource({"1"})
    public void findByIdTest(Long id) {
        try {
            given().
                pathParam("id", id).
            when().
                get("addresses/{id}").
            then().
                statusCode(200).
                body("id", equalTo(id.intValue()));
        } catch (Exception ex) {
            throw new CheckValueException("Field id is mandatory");
        }
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
