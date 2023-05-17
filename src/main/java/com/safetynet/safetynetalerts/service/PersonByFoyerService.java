package com.safetynet.safetynetalerts.service;

import lombok.Data;

@Data
public class PersonByFoyerService {

	private String firstName;

	private String lastName_medications_allergies;

	private String phone;

	private int age;

	public PersonByFoyerService(String firstName, String lastName_medications_allergies, String phone, int age) {
		super();
		this.firstName = firstName;
		this.lastName_medications_allergies = lastName_medications_allergies;
		this.phone = phone;
		this.age = age;
	}

}
