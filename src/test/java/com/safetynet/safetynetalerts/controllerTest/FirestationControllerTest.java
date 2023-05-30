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
class FirestationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getFirestationsTest() throws Exception {
		mockMvc.perform(get("/firestation")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].address", is("1509 Culver St")));
	}

	@Test
	void postFirestationsTest() throws Exception {
		mockMvc.perform(post("/firestation").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("{ \"address\":\"29 15th St\", \"station\":\"2\" }").accept(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("la firestation 29 15th St Station:2 deja presente")));
	}

	@Test
	void patchFirestationsTest() throws Exception {
		mockMvc.perform(patch("/firestation").contentType(MediaType.APPLICATION_JSON_VALUE)
				.content("{ \"address\":\"90 15th St\", \"station\":\"500\" }")
				.accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("la firestation 90 15th St n'est pas reference")));
	}

	@Test
	void deleteFirestationsTest() throws Exception {
		mockMvc.perform(delete("/firestation/10").accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("la firestation 10 n'est pas reference")));
	}

}
