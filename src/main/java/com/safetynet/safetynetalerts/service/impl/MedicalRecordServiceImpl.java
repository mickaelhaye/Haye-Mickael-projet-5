package com.safetynet.safetynetalerts.service.impl;

import com.safetynet.safetynetalerts.model.MedicalrecordModel;

public interface MedicalRecordServiceImpl {
	public String addMedicalRecord(MedicalrecordModel medicalRecord);

	public String updateMedicalRecord(MedicalrecordModel medicalRecord);

	public String deleteMedicalRecord(String firstName, String lastName);

}
