package com.safetynet.safetynetalerts;

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
	private String jsonFileTestPath;
	private String jsonFileTestWritePath;

	public String getJsonFilePath() {
		return jsonFilePath;
	}

	public void setJsonFilePath(String jsonFilePath) {
		this.jsonFilePath = jsonFilePath;
	}

	public String getJsonFileTestPath() {
		return jsonFileTestPath;
	}

	public void setJsonFileTestPath(String jsonFileTestPath) {
		this.jsonFileTestPath = jsonFileTestPath;
	}

	public String getJsonFileTestWritePath() {
		return jsonFileTestWritePath;
	}

	public void setJsonFileTestWritePath(String jsonFileTestWritePath) {
		this.jsonFileTestWritePath = jsonFileTestWritePath;
	}

}
