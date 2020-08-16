package com.ksm.wstbackoffice.controller;

import com.ksm.wstbackoffice.exception.CheckValueException;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:sql/countryInsert.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:sql/countryDelete.sql")
})
public class CountryControllerIT extends BaseControllerIT {
    @ParameterizedTest
    @CsvSource({
            ",",
            "' '"
    })
    public void findByIdinvalidValuesAreNotAllowedTest(String iso3166) {
        Throwable thrown = catchThrowable(() -> findByIdTest(iso3166));
        assertThat(thrown)
                .isInstanceOf(CheckValueException.class)
                .hasMessageContaining("iso3166 is mandatory");
    }

    @ParameterizedTest
    @CsvSource({"060"})
    public void findByIdTest(String iso3166) {
        try {
            given().
                pathParam("iso3166", iso3166).
            when().
                get("countries/{iso3166}", iso3166).
            then().
                statusCode(200).
                body("iso3166", is(iso3166));
        } catch (Exception ex) {
            throw new CheckValueException("Field iso3166 is mandatory");
        }
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
