package com.safetynet.safetynetalerts.controllerTest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class MedicalrecordControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getMedicalrecordsTest() throws Exception {
		mockMvc.perform(get("/medicalRecord")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].firstName", is("John")));
	}

	@Test
	void postMedicalrecordsTest() throws Exception {
		mockMvc.perform(post("/medicalRecord").contentType(MediaType.APPLICATION_JSON_VALUE).content(
				"{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("John Boyd ajoute")));
	}

	@Test
	void patchMedicalrecordsTest() throws Exception {
		mockMvc.perform(patch("/medicalRecord").contentType(MediaType.APPLICATION_JSON_VALUE).content(
				"{ \"firstName\":\"Johnny\", \"lastName\":\"Boyd\", \"birthdate\":\"03/06/1984\", \"medications\":[\"aznol:350mg\", \"hydrapermazol:100mg\"], \"allergies\":[\"nillacilan\"] }")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Johnny Boyd n'est pas reference")));
	}

	@Test
	void deleteMedicalrecordsTest() throws Exception {
		mockMvc.perform(delete("/medicalRecord/Johnny/Boyd").accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("Johnny Boyd n'est pas reference")));
	}

}
