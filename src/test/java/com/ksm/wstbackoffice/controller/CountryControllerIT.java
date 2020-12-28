package com.ksm.wstbackoffice.controller;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;

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

    public static Stream<Arguments> findCountriesStartWithTestArguments() {
        return Stream.of(
                Arguments.of(Arrays.asList(), 400),
                Arguments.of(Arrays.asList(""), 404),
                Arguments.of(Arrays.asList(" "), 404),
                Arguments.of(Arrays.asList("a"), 200),
                Arguments.of(Arrays.asList("a "), 200),
                Arguments.of(Arrays.asList("a","a"), 200),
                Arguments.of(Arrays.asList("a", "ua", "b"), 200),
                Arguments.of(Arrays.asList("a","au"), 200)
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
            body("size()", equalTo(5));
    }

    @ParameterizedTest
    @MethodSource("findCountriesStartWithTestArguments")
    public void findCountriesStartWith(List<String> stringsToStartWith, int expectedStatus) {
        given().
            contentType(ContentType.JSON).
            body(stringsToStartWith).
        when().
            post("countries/start-with").
        then().
            statusCode(expectedStatus);
    }
}
