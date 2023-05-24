package com.safetynet.safetynetalerts;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * Cette classe permet de récupérer des informations du fichier
 * application.properties.
 * 
 * @author Mickael Hayé
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "com.safetynet.safetynetalerts")
public class CustomProperties {
	private String jsonFilePath;
	private String jsonFileTestPath;
	private String jsonFileTestWritePath;
}
