package com.safetynet.safetynetalerts.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class PersonModel {

	private String firstName;

	private String lastName;

	private String address;

	private String city;

	private String zip;

	private String phone;

	private String email;

}
