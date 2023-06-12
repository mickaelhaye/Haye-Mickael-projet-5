package com.safetynet.safetynetalerts.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Cette classe permet de récupérer des informations du fichier
 * application.properties.
 * 
 * @author Mickael Hayé
 */

@Configuration
@ConfigurationProperties(prefix = "com.safetynet.safetynetalerts")
public class CustomProperties {
	private String jsonFilePath;

	public String getJsonFilePath() {
		return jsonFilePath;
	}

	public void setJsonFilePath(String jsonFilePath) {
		this.jsonFilePath = jsonFilePath;
	}

}
