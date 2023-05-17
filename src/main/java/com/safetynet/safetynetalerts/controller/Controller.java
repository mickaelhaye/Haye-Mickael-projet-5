package com.safetynet.safetynetalerts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.repository.JsonFileReadRepository;

import jakarta.annotation.PostConstruct;
import lombok.Data;

@RestController
@Data
public class Controller {

	@Autowired
	private JsonFileReadRepository jsonFileRecup;

	private FileEntryRepository file;

	@PostConstruct
	// Recupération du fichier json
	public void init() {

		file = jsonFileRecup.recupFile();
	}

}
