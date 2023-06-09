package com.safetynet.safetynetalerts.service.impl;

import java.util.List;

import com.safetynet.safetynetalerts.model.ChildAlertByAddressModel;
import com.safetynet.safetynetalerts.model.PersonModel;

public interface PersonServiceImpl {

	public List<ChildAlertByAddressModel> findByAddressAListChild(String address) throws Exception;

	public List<Object> findByAddressAPerson(String address) throws Exception;

	public List<String> findByFirstNameAPerson(String firstName, String lastName) throws Exception;

	public List<String> findByCityAEmail(String city) throws Exception;

	public String addPerson(PersonModel person);

	public String updatePerson(PersonModel person);

	public String deletePerson(String firstName, String lastName);
}
