package com.safetynet.safetynetalerts.controllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getPersonsTest() throws Exception {
		mockMvc.perform(get("/person")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void postPersonsTest() throws Exception {
		mockMvc.perform(post("/person").contentType(MediaType.APPLICATION_JSON_VALUE).content(
				"{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void patchPersonsTest() throws Exception {
		mockMvc.perform(patch("/person").contentType(MediaType.APPLICATION_JSON_VALUE).content(
				"{ \"firstName\":\"John\", \"lastName\":\"Boyd\", \"address\":\"1509 Culver St\", \"city\":\"Culver\", \"zip\":\"97451\", \"phone\":\"841-874-6512\", \"email\":\"jaboyd@email.com\" }")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void deletePersonsTest() throws Exception {
		mockMvc.perform(delete("/person/JohnBoyd").accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void getAfficherUneListePersonneTest() throws Exception {
		mockMvc.perform(get("/firestation/3")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void getafficherUneListeEnfantTest() throws Exception {
		mockMvc.perform(get("/childAlert/1509 Culver St")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void afficherUneListeNumTelephoneTest() throws Exception {
		mockMvc.perform(get("/phoneAlert/3")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void afficherUneListePersonneParAddresseTest() throws Exception {
		mockMvc.perform(get("/fire/1509 Culver St")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void afficherUneListeFoyerParFirestationTest() throws Exception {
		mockMvc.perform(get("/flood/stations/3")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void afficherUneListePersonneParPrenomTest() throws Exception {
		mockMvc.perform(get("/personInfo/John")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	void afficherEmailParCityTest() throws Exception {
		mockMvc.perform(get("/communityEmail/Culver")).andDo(print()).andExpect(status().isOk());
	}

}
