package com.ksm.wstbackoffice;

import com.ksm.wstbackoffice.controller.AddressControllerTest;
import com.ksm.wstbackoffice.controller.CountryControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

@SpringBootTest
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts="classpath:sql/beforeTestRun.sql"),
		@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts="classpath:sql/afterTestRun.sql")
})
@ActiveProfiles("test")
class WstBackofficeApplicationTests {

	@Autowired
	private CountryControllerTest countryControllerTest;

	@Autowired
	private AddressControllerTest addressControllerTest;

	@Test
	void contextLoads() {
		countryControllerTest.findAllTest();
		countryControllerTest.findByIdTest();

		addressControllerTest.findByIdTest();
		addressControllerTest.findAllTest();
		addressControllerTest.createTest();
	}
}
