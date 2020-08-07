package com.ksm.wstbackoffice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(properties = {"spring.autoconfigure.exclude=com.integralblue.log4jdbc.spring.Log4jdbcAutoConfiguration"})
@ActiveProfiles("test")
class WstBackofficeApplicationTests {

	@Test
	void contextLoads() {

	}
}
