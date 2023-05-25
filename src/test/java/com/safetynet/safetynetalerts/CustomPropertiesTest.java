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

	@Test
	void setGetJsonFileTestPathTest() {
		prop.setJsonFileTestPath("test");
		assertEquals("test", prop.getJsonFileTestPath());
	}

	@Test
	void setGetJsonFileTestWritePathTest() {
		prop.setJsonFileTestWritePath("test");
		assertEquals("test", prop.getJsonFileTestWritePath());
	}

}
