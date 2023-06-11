package com.safetynet.safetynetalerts.service;

import com.safetynet.safetynetalerts.model.MedicalrecordModel;

public interface MedicalRecordService {
	public String addMedicalRecord(MedicalrecordModel medicalRecord);

	public String updateMedicalRecord(MedicalrecordModel medicalRecord);

	public String deleteMedicalRecord(String firstName, String lastName);

}
