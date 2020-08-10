package com.ksm.wstbackoffice.controller;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:sql/countryInsert.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:sql/countryDelete.sql")
})
public class CountryControllerIT extends BaseControllerIT {

    @Test
    public void findByIdTest() {
        given().
            pathParam("iso3166", "060").
        when().
            get("countries/{iso3166}").
        then().
            statusCode(200).
            body("iso3166", is("060"), "name", is("Bermuda"));
    }

    @Test
    public void findAllTest() {
        when().
            get("countries").
        then().
            statusCode(200).
            body("size()", equalTo(5));
    }
}
