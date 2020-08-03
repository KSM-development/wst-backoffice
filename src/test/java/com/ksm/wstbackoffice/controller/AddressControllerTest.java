package com.ksm.wstbackoffice.controller;

import com.ksm.wstbackoffice.endpoint.EndPoint;
import com.ksm.wstbackoffice.entity.Address;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.mapper.TypeRef;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class AddressControllerTest {

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
        Address address = given()
                .spec(spec)
                .pathParam("id", 1)
                .when()
                .get(EndPoint.addressesWithId)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(Address.class);

        Assert.assertNotNull(address);
        assertThat(address.getId(), Matchers.is(1L));
    }

    @Test
    public void findAllTest() {
        List<Address> addresses = given()
                .spec(spec)
                .when()
                .get(EndPoint.addresses)
                .then()
                .statusCode(200)
                .extract()
                .body()
                .as(new TypeRef<List<Address>>() {
                });

        Assert.assertNotNull(addresses);
        assertThat(addresses.size(), Matchers.greaterThanOrEqualTo(0));
    }

    @Test
    public void createTest() {
        Map<String, Object> country = new HashMap<>();
        country.put("iso3166", "061");
        country.put("name", "Бермуды 061");

        Map<String, Object> address = new HashMap<>();
        address.put("zipcode", "zipcode1");
        address.put("region", "region1");
        address.put("district", "district1");
        address.put("city", "city1");
        address.put("street", "street1");
        address.put("houseNumber", "1a");
        address.put("apartmentNumber", "11");
        address.put("description", "description1");
        address.put("country", country);

        Address retrievedAddress = given()
                .spec(spec)
                .body(address)
                .when()
                .post(EndPoint.addresses)
                .then()
                .statusCode(201)
                .extract()
                .as(Address.class);

        Assert.assertNotNull(retrievedAddress);
        Assert.assertNotNull(retrievedAddress.getId());
        Assert.assertEquals(address.get("zipcode"), retrievedAddress.getZipcode());
        Assert.assertEquals(address.get("description"), retrievedAddress.getDescription());
        Assert.assertNotNull(retrievedAddress.getCountry().getISO3166());
        Assert.assertEquals(country.get("name"), retrievedAddress.getCountry().getName());
    }
}
