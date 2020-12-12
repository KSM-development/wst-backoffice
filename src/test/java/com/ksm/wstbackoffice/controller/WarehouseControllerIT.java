package com.ksm.wstbackoffice.controller;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.stream.Stream;

import static io.restassured.RestAssured.given;

@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:sql/warehouseInsert.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:sql/warehouseDelete.sql")
})
public class WarehouseControllerIT extends BaseControllerIT {
    public static Stream<Arguments> findByIdTestArguments() {
        return Stream.of(
                Arguments.of(-1L, 200),
                Arguments.of(-100L, 404),
                Arguments.of(0L, 404),
                Arguments.of(Long.MAX_VALUE, 404)
        );
    }

    @ParameterizedTest
    @MethodSource("findByIdTestArguments")
    public void findByIdTest(Long id, int expectedStatus) {
        given().
                pathParam("id", id).
                when().
                get("warehouses/{id}").
                then().
                statusCode(expectedStatus);
    }
}
