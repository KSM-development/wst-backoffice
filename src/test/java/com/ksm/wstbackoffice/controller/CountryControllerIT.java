package com.ksm.wstbackoffice.controller;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.containsInAnyOrder;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:sql/countryInsert.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:sql/countryDelete.sql")
})
public class CountryControllerIT extends BaseControllerIT {
    public static Stream<Arguments> findByIdTestArguments() {
        return Stream.of(
                Arguments.of("AUS", 200),
                Arguments.of("ASS", 200),
                Arguments.of("6", 400),
                Arguments.of("06", 400),
                Arguments.of("0689", 400),
                Arguments.of("06j", 400),
                Arguments.of("doe", 400),
                Arguments.of("067", 400),
                Arguments.of(",", 400),
                Arguments.of(" ", 400),
                Arguments.of("''", 400),
                Arguments.of("' '", 400),
                Arguments.of("XXX", 404)
        );
    }

    public static Stream<Arguments> badArgumentsProvider() {
        return Stream.of(
                Arguments.of(Arrays.asList(), 400),
                Arguments.of(Arrays.asList(""), 400)
        );
    }

    public static Stream<Arguments> correctArgumentsProvider() {
        return Stream.of(
                Arguments.of("amer", Arrays.asList("United States of America"), 200),
                Arguments.of("kor", Arrays.asList("North Korea"), 200),
                Arguments.of("sa", Arrays.asList("Saint Kitts and Nevis", "Saint Lucia",
                        "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe",
                        "Saudi Arabia", "South Georgia and the South Sandwich Islands"), 200)
        );
    }

    @ParameterizedTest
    @MethodSource("findByIdTestArguments")
    public void findByIdTest(String alpha3code, int expectedStatus) {
        given().
            pathParam("alpha3code", alpha3code).
        when().
            get("countries/{alpha3code}", alpha3code).
        then().
            statusCode(expectedStatus);
    }

    @Test
    public void findAllTest() {
        when().
            get("countries").
        then().
            statusCode(200).
            body("size()", equalTo(21));
    }

    @ParameterizedTest
    @MethodSource("badArgumentsProvider")
    public void findAllByBadNameStartsWithFiltersTest(List<String> nameStartsWithFilters, int expectedStatus) {
        given().
            queryParam("nameStartsWithFilters", nameStartsWithFilters).
        when().
            get("countries").
        then().
            statusCode(expectedStatus);
    }

    @ParameterizedTest
    @MethodSource("correctArgumentsProvider")
    public void findAllByCorrectNameStartsWithFiltersTest(String nameStartsWithFilters, List<String> expectedData, int expectedStatus) {
        given().
            queryParam("nameStartsWithFilters", nameStartsWithFilters).
        when().
            get("countries").
        then().
            statusCode(expectedStatus).
            body("total.".concat(nameStartsWithFilters), equalTo(expectedData.size())).
            body("details.".concat(nameStartsWithFilters), containsInAnyOrder(expectedData.toArray()));
    }

}
