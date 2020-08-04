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
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.MatcherAssert.assertThat;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CountryControllerTest {

    private static RequestSpecification spec;

    public CountryControllerTest() {
        spec = new RequestSpecBuilder()
                .setContentType(JSON)
                .setBaseUri(RestAssured.DEFAULT_URI)
                .setPort(EndPoint.PORT)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @Test
    public void findByIdTest() {
        Country country =
                given()
                    .spec(spec)
                    .pathParam("iso3166", "060")
                .when()
                    .get(EndPoint.COUNTRY_ID)
                .then()
                    .statusCode(200)
                .extract()
                    .body()
                    .as(Country.class);

        Assert.assertNotNull(country);
        assertThat(country.getISO3166(), Matchers.is("060"));
    }

    @Test
    public void findAllTest() {
        List<Country> countries =
                given()
                    .spec(spec)
                .when()
                    .get(EndPoint.COUNTRIES)
                .then()
                    .statusCode(200)
                .extract()
                    .body()
                    .as(new TypeRef<List<Country>>(){});

        Assert.assertNotNull(countries);
        assertThat(countries.size(), Matchers.greaterThanOrEqualTo(0));
    }
}
