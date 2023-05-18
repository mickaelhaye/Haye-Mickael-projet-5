package com.safetynet.safetynetalerts.repository;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.CustomProperties;
import com.safetynet.safetynetalerts.controller.FirestationController;

@Repository
public class JsonFileReadRepository {

	private static Logger logger = LoggerFactory.getLogger(FirestationController.class);

	@Autowired
	private CustomProperties prop;

	public FileEntryRepository recupFile() {

		FileEntryRepository file = null;
		// Recupération des données dans le fichier json
		ObjectMapper objectMapper = new ObjectMapper();
		String path = prop.getJsonFilePath();

		try {

			file = objectMapper.readValue(new File(path), FileEntryRepository.class);
			logger.debug("Lecture du fichier Json OK");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("Lecture du fichier Json ECHEC" + e);
		}

		return file;
	}

}
