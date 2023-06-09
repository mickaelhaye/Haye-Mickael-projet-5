package com.safetynet.safetynetalerts.configurationTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.safetynet.safetynetalerts.configuration.CustomProperties;

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
