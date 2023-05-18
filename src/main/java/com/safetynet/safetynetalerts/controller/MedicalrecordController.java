package com.safetynet.safetynetalerts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.safetynetalerts.model.MedicalrecordModel;
import com.safetynet.safetynetalerts.repository.FileEntryRepository;
import com.safetynet.safetynetalerts.repository.JsonFileWriteRepository;
import com.safetynet.safetynetalerts.service.MedicalRecordService;

@RestController
public class MedicalrecordController {

	@Autowired
	private Controller controller;

	@Autowired
	private JsonFileWriteRepository jsonFileWrite;

	@Autowired
	private MedicalRecordService medicalrecordService;

	private FileEntryRepository file;

	@GetMapping(value = "/medicalrecord")
	public List<MedicalrecordModel> afficherListeMedicalrecord() {
		majPointeur();
		return file.getMedicalrecords();
	}

	@PostMapping("/medicalRecord")
	public String ajouterMedicalRecord(@RequestBody MedicalrecordModel medicalrecord) {
		majPointeur();
		String sVal = medicalrecordService.addMedicalRecord(medicalrecord);
		jsonFileWrite.writeFile(file);
		return sVal;
	}

	@PatchMapping("/medicalRecord")
	public String mettreAJourMedicalRecord(@RequestBody MedicalrecordModel medicalrecord) {
		majPointeur();
		String sVal = medicalrecordService.updateMedicalRecord(medicalrecord);
		jsonFileWrite.writeFile(file);
		return sVal;
	}

	@DeleteMapping("/medicalRecord/{firstNameLastName}")
	public String supprimerMedicalRecord(@PathVariable String firstNameLastName) {
		majPointeur();
		String sVal = medicalrecordService.deleteMedicalRecord(firstNameLastName);
		jsonFileWrite.writeFile(file);
		return sVal;
	}

	void majPointeur() {
		file = controller.getFile();// à valider
		medicalrecordService.setMedicalrecords(file.getMedicalrecords()); // à valider
	}

}
