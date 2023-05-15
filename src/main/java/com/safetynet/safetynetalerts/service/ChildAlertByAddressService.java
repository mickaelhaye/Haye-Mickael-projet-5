package com.safetynet.safetynetalerts.service;

import java.util.ArrayList;

import lombok.Data;

@Data
public class ChildAlertByAddressService {
	private String firstName;

	private String lastName;

	private int age;

	ArrayList<String> personnDansMemeFoyer = new ArrayList<String>();

	public ChildAlertByAddressService(String firstName, String lastName, int age,
			ArrayList<String> personnDansMemeFoyer) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.personnDansMemeFoyer = personnDansMemeFoyer;

	}

}
