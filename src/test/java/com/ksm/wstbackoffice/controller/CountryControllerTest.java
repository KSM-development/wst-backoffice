package com.ksm.wstbackoffice.controller;

import com.ksm.wstbackoffice.endpoint.EndPoint;
import com.ksm.wstbackoffice.entity.Country;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.mapper.TypeRef;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.MatcherAssert.assertThat;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class CountryControllerTest {

    private static RequestSpecification spec;

    @BeforeAll
    public static void initSpec() {
        spec = new RequestSpecBuilder()
                .setContentType(JSON)
                .setBaseUri(RestAssured.DEFAULT_URI)
                .setPort(EndPoint.port)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @Test
    public void findByIdTest() {
        Country country = given()
                .spec(spec)
                .pathParam("iso3166", "061")
                .when()
                .get(EndPoint.countryWithId)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Country.class);

        Assert.assertNotNull(country);
        assertThat(country.getISO3166(), Matchers.is("061"));
    }

    @Test
    public void findAllTest() {
        List<Country> countries = given()
                .spec(spec)
                .when()
                .get(EndPoint.countries)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(new TypeRef<List<Country>>() {
                });

        Assert.assertNotNull(countries);
        assertThat(countries.size(), Matchers.greaterThanOrEqualTo(0));
    }
}
