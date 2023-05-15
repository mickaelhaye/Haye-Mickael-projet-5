package com.safetynet.safetynetalerts;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.safetynet.safetynetalerts")
public class CustomProperties {
	private String jsonFilePath;
}