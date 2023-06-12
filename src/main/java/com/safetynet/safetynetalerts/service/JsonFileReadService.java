package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.FileEntryModel;

public interface JsonFileReadService {

	public FileEntryModel recupFile() throws Exception;

	public FileEntryModel getFile();

}
