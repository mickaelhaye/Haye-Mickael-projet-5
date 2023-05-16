package com.safetynet.safetynetalerts.repository;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.safetynetalerts.CustomProperties;

@Repository
public class JsonFileWriteRepository {

	private FileEntryModelRepository file = new FileEntryModelRepository();
	@Autowired
	private CustomProperties prop;

	public FileEntryModelRepository writeFile(FileEntryModelRepository file2) {

		FileEntryModelRepository file = null;
		// Recupération des données dans le fichier json
		ObjectMapper objectMapper = new ObjectMapper();
		String path = prop.getJsonFilePath();

		try {
			objectMapper.writeValue(new File(path), file2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return file;
	}

}
