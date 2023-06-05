package com.safetynet.safetynetalerts.model;

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
public class ChildAlertByAddressModel {
	private String firstName;

	private String lastName;

	private int age;

	ArrayList<String> personnDansMemeFoyer = new ArrayList<String>();

}
