package com.duru.profileservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "spring.cloud.config.enabled=false",
        "spring.config.import=optional:"
})
class ProfileServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
