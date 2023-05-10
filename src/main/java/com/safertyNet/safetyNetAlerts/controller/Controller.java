package com.safertyNet.safetyNetAlerts.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Controller {

	public void start() throws StreamReadException, DatabindException, IOException {

		// Recupération des données dans le fichier json

		ObjectMapper objectMapper = new ObjectMapper();
		String path = "src/main/resources/data/data.json";
		Map<String, Object> map = objectMapper.readValue(new File(path), new TypeReference<Map<String, Object>>() {
		});
		System.out.println(map);

	}

}
