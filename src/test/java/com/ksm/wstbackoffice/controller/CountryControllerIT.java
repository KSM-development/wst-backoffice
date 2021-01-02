package com.ksm.wstbackoffice.controller;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

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

    public static Stream<Arguments> findAllByTestArguments() {
        return Stream.of(
                Arguments.of(Arrays.asList(), 400),
                Arguments.of(Arrays.asList(""), 200),
                Arguments.of(Arrays.asList(" "), 200),
                Arguments.of(Arrays.asList("a"), 200),
                Arguments.of(Arrays.asList("a "), 200),
                Arguments.of(Arrays.asList("a","a"), 200),
                Arguments.of(Arrays.asList("a", "ua", "b"), 200),
                Arguments.of(Arrays.asList("a","au"), 200)
        );
    }

    public static Stream<Arguments> findAllByFilterNameTestArguments() {
        return Stream.of(
                Arguments.of(Arrays.asList("sa"),
                        Arrays.asList("Saudi Arabia", "South Georgia and the South Sandwich Islands",
                                "Saint Lucia", "Saint Vincent and the Grenadines", "Sao Tome and Principe",
                                "San Marino", "Saint Kitts and Nevis", "Samoa"))
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
    @MethodSource("findAllByTestArguments")
    public void findAllByTest(List<String> nameStartsWithFilters, int expectedStatus) {
        given().
            contentType(ContentType.JSON).
            body(nameStartsWithFilters).
        when().
            get("countries/filter-name").
        then().
            statusCode(expectedStatus);
    }

    @ParameterizedTest
    @MethodSource("findAllByFilterNameTestArguments")
    public void findAllByTest(List<String> nameStartsWithFilters, List<String> expected) {
        given().
            contentType(ContentType.JSON).
            body(nameStartsWithFilters).
        when().
            get("countries/filter-name").
        then().
            statusCode(200)
            .body("details.sa", hasItems(expected.toArray()))
            .body("details.sa.size", equalTo(8));
    }

    @Test
    public void findAllByContainMoreThanOneWordTest() {
        List<String> nameStartsWithFilters = Arrays.asList("kor", "amer", "co");

        given().
            contentType(ContentType.JSON).
            body(nameStartsWithFilters).
        when().
            get("countries/filter-name").
        then().
            statusCode(200)
            .body("details.kor", hasItems("North Korea"))
            .body("details.kor.size", equalTo(1))
            .body("details.amer", hasItems("United States of America"))
            .body("details.amer.size", equalTo(1))
            .body("details.co", hasItems("Democratic Republic of the Congo"))
            .body("details.co.size", equalTo(1));
    }
}
