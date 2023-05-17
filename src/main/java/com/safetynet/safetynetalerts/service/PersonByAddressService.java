package com.safetynet.safetynetalerts.service;

import java.util.ArrayList;

import lombok.Data;

@Data
public class PersonByAddressService {

	private String firstName;

	private String lastName;

	private String phone;

	private int age;

	ArrayList<String> medications = new ArrayList<String>();

	ArrayList<String> allergies = new ArrayList<String>();

	public PersonByAddressService(String firstName, String lastName, String phone, int age,
			ArrayList<String> medications, ArrayList<String> allergies) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.age = age;
		this.medications = medications;
		this.allergies = allergies;
	}

}
