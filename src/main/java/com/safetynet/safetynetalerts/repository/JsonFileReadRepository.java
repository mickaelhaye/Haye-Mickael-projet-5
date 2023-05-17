package com.safetynet.safetynetalerts.repository;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.CustomProperties;

@Repository
public class JsonFileReadRepository {

	@Autowired
	private CustomProperties prop;

	public FileEntryRepository recupFile() {

		FileEntryRepository file = null;
		// Recupération des données dans le fichier json
		ObjectMapper objectMapper = new ObjectMapper();
		String path = prop.getJsonFilePath();

		try {

			file = objectMapper.readValue(new File(path), FileEntryRepository.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return file;
	}

}
