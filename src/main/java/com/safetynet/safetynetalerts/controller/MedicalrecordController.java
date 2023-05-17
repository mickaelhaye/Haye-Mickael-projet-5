package com.safetynet.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.model.MedicalrecordModel;
import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.repository.JsonFileWriteRepository;

@RestController
public class MedicalrecordController {

	@Autowired
	private Controller controller;

	@Autowired
	private JsonFileWriteRepository jsonFileWrite;

	private FileEntryRepository file;

	@GetMapping(value = "/medicalrecord")
	public List<MedicalrecordModel> afficherListeMedicalrecord() {
		file = controller.getFile();// à valider
		return file.getMedicalrecords();
	}

	@PostMapping("/medicalRecord")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String ajouterMedicalRecord(@RequestBody MedicalrecordModel medicalrecord) {
		file = controller.getFile();// à valider
		String sVal = file.addMedicalRecord(medicalrecord);
		jsonFileWrite.writeFile(file);
		return sVal;
	}

	@PatchMapping("/medicalRecord")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String mettreAJourMedicalRecord(@RequestBody MedicalrecordModel medicalrecord) {
		file = controller.getFile();// à valider
		String sVal = file.updateMedicalRecord(medicalrecord);
		jsonFileWrite.writeFile(file);
		return sVal;
	}

	@DeleteMapping("/medicalRecord/{firstNameLastName}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public String mettreAJourMedicalRecord(@PathVariable String firstNameLastName) {
		file = controller.getFile();// à valider
		String sVal = file.deleteMedicalRecord(firstNameLastName);
		jsonFileWrite.writeFile(file);
		return sVal;
	}

}
