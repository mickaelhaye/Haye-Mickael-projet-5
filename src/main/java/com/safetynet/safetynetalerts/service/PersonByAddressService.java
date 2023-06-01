package com.safetynet.safetynetalerts.service;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * classe permettant de mettre en forme des données pour une API
 * 
 * @author Mickael Hayé
 *
 */
@Data
@AllArgsConstructor
public class PersonByAddressService {

	private String firstName;

	private String lastName;

	private String phone;

	private int age;

	ArrayList<String> medications = new ArrayList<String>();

	ArrayList<String> allergies = new ArrayList<String>();
}
