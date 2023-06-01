package com.safetynet.safetynetalerts;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CustomPropertiesTest {

	@Autowired
	private CustomProperties prop;

	@Test
	void setGetJsonFilePathTest() {
		prop.setJsonFilePath("test");
		assertEquals("test", prop.getJsonFilePath());
	}
}
