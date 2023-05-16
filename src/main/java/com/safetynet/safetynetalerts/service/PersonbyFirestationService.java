package com.safetynet.safetynetalerts.service;

import lombok.Data;

@Data
public class PersonbyFirestationService {
	private String firstName;

	private String lastName;

	private String address;

	private String phone;

	public PersonbyFirestationService(String firstName, String lastName, String address, String phone) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
	}

}
