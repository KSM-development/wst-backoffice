package com.ksm.wstbackoffice.controller;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.stream.Stream;

@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:sql/countryInsert.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:sql/countryDelete.sql")
})
public class CountryControllerIT extends BaseControllerIT {
    public static Stream<Arguments> findByIdTestArguments() {
        return Stream.of(
                Arguments.of("060", 200),
                Arguments.of("804", 200),
                Arguments.of("6", 400),
                Arguments.of("06", 400),
                Arguments.of("0689", 400),
                Arguments.of("06j", 400),
                Arguments.of("doe", 400),
                Arguments.of(",", 400),
                Arguments.of(" ", 400),
                Arguments.of("''", 400),
                Arguments.of("' '", 400),
                Arguments.of("999", 404)
        );
    }

    @ParameterizedTest
    @MethodSource("findByIdTestArguments")
    public void findByIdTest(String iso3166, int expectedStatus) {
        given().
            pathParam("iso3166", iso3166).
        when().
            get("countries/{iso3166}", iso3166).
        then().
            statusCode(expectedStatus);
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
